package com.example.kotryn.processes;

import com.example.kotryn.csv.CSVMyReader;
import com.example.kotryn.csv.File;
import com.example.kotryn.csv.FileFactory;
import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ProcessCalculatingStatistic implements IProcess {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public ProcessCalculatingStatistic(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobId = jobId;
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    private void toBeDoneInsideProcessAtBegin(int pid) {
        // update processDescriptorRepository
        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
        processDescriptor.setProcessState(ProcessState.IN_PROGRESS);
        processDescriptor.setPid(pid);
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    private void toBeDoneInsideProcessAtEndWhenSuccess() {
        // update jobRepository
        Job job = jobRepository.findOne(jobId);

        String csvFile = FileFactory.getFile(File.CALCULATING_STATISTIC);
        CSVMyReader readFile = new CSVMyReader(csvFile);

        job.setX(readFile.csvGetOneColumn(0));

        List<String> stringList = readFile.csvGetOneColumn(1);
        List<Double> doubleList = new ArrayList<>();

        for(String item : stringList){
            doubleList.add(Double.parseDouble(item));
        }

        job.setY(doubleList);
        jobRepository.saveAndFlush(job);

        // update processDescriptorRepository
        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
        processDescriptor.setProcessState(ProcessState.COMPLETED_SUCCESS);
        LocalDateTime startTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime stopTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 40, 10);
        processDescriptor.setDuration(Duration.between(startTime, stopTime));
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    private void toBeDoneInsideProcessAtEndWhenFailure() {
        // update processDescriptorRepository
        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
        processDescriptor.setProcessState(ProcessState.COMPLETED_FAILURE);
        LocalDateTime startTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime stopTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 30, 15);
        processDescriptor.setDuration(Duration.between(startTime, stopTime));
        processDescriptor.setErrorMessage("calculating statistic failed");
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    @Override
    public void start() {
        // launching should be done in a separate thread so as not to block UI
        new Thread(() -> {
            try {
                ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
                processDescriptor.setHost("Lenovo-320");
                processDescriptor.setSystemType(OSInfo.getOs());
                int pid = -1;
                Process process;
                if(processDescriptor.getSystemType() == SystemType.LINUX){
                    String command = "xterm  -e ./file8.sh " + jobId;
                    process = Runtime.getRuntime().exec(command);

                    try {
                        if (process.getClass().getName().equals("java.lang.UNIXProcess")) {
                            Field f = process.getClass().getDeclaredField("pid");
                            f.setAccessible(true);
                            pid = (int) f.getLong(process);
                            f.setAccessible(false);
                        }
                    } catch (Exception e) {
                        pid = -1;
                    }
                }/*else if(processDescriptor.getSystemType() == SystemType.WINDOWS){
                    command = "cmd.exe /c start /w cmd.exe /c \"echo Calculating statistic for job ID: " + jobId + "&& timeout 15\"";
                }*/else{
                    throw new InterruptedException();
                }

                toBeDoneInsideProcessAtBegin(pid);
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    toBeDoneInsideProcessAtEndWhenSuccess();
                } else {
                    toBeDoneInsideProcessAtEndWhenFailure();
                }
            } catch (IOException | InterruptedException e) {
                toBeDoneInsideProcessAtEndWhenFailure();
            }
        }).start();
    }

    @Override
    public void interrupt() {
        new Thread(() -> {
            try {
                ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
                processDescriptor.setProcessState(ProcessState.UNKNOWN);
                processDescriptorRepository.saveAndFlush(processDescriptor);
                String command = "kill -9 "+processDescriptor.getPid();
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                //toBeDoneInsideProcessAtEndWhenFailure();
                throw new UnsupportedOperationException("Not yet implemented");
            }
        }).start();
    }
}