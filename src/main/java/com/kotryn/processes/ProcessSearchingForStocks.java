package com.kotryn.processes;

import com.kotryn.csv.CSVMyReader;
import com.kotryn.csv.File;
import com.kotryn.csv.FileFactory;
import com.kotryn.entity.Job;
import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.repository.JobRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.repository.SectorRepository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public class ProcessSearchingForStocks implements IProcess {

    private JobRepository jobRepository;
    private SectorRepository sectorRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public ProcessSearchingForStocks(Long jobId, JobRepository jobRepository, SectorRepository sectorRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobId = jobId;
        this.jobRepository = jobRepository;
        this.sectorRepository = sectorRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    public void toBeDoneInsideProcessAtBegin(int pid) {
        // update processDescriptorRepository
        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
        processDescriptor.setProcessState(ProcessState.IN_PROGRESS);
        processDescriptor.setPid(pid);
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    public void toBeDoneInsideProcessAtEndWhenSuccess() {
        // update jobRepository
        Job job = jobRepository.findOne(jobId);

        String csvFile = FileFactory.getFile(File.FIRST_STOCK);
        CSVMyReader readFile = new CSVMyReader(csvFile, sectorRepository, job);
        readFile.csvFirstSetStocks();
        job.setStocks(readFile.getStocksMap());

        jobRepository.saveAndFlush(job);

        // update processDescriptorRepository
        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
        processDescriptor.setProcessState(ProcessState.COMPLETED_SUCCESS);
        LocalDateTime startTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime stopTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 40, 10);
        processDescriptor.setDuration(Duration.between(startTime, stopTime));
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    public void toBeDoneInsideProcessAtEndWhenFailure() {
        // update processDescriptorRepository
        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
        processDescriptor.setProcessState(ProcessState.COMPLETED_FAILURE);
        LocalDateTime startTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 20, 55);
        LocalDateTime stopTime = LocalDateTime.of(2016, Month.AUGUST, 31, 10, 30, 15);
        processDescriptor.setDuration(Duration.between(startTime, stopTime));
        processDescriptor.setErrorMessage("no stocks available in the database");
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    @Override
    public void start() {
        // launching should be done in a separate thread so as not to block UI
        new Thread(() -> {
            try {
                ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
                processDescriptor.setHost("10.42.0.176");
                processDescriptor.setSystemType(OSInfo.getOs());
                int pid = -1;
                Process process;
                if(processDescriptor.getSystemType() == SystemType.LINUX){
                    String command = "xterm  -e ./file.sh " + jobId;
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
                }
                /*else if(processDescriptor.getSystemType() == SystemType.WINDOWS){
                    command = "cmd.exe /c start /w cmd.exe /c \"echo Searching for stocks for job ID: " + jobId + "&& timeout 15\"";
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
                throw new UnsupportedOperationException();
            }
        }).start();
    }
}
