package com.kotryn.processes;

import com.kotryn.csv.CSVMyReader;
import com.kotryn.csv.File;
import com.kotryn.csv.FileFactory;
import com.kotryn.entity.Job;
import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.repository.GrowthStockSectorRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.repository.ProcessDescriptorRepository;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public class ProcessEstimatingGrowthStocks implements IProcess {
    private JobRepository jobRepository;
    private GrowthStockSectorRepository growthStockSectorRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public ProcessEstimatingGrowthStocks(Long jobId, JobRepository jobRepository, GrowthStockSectorRepository growthStockSectorRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobId = jobId;
        this.growthStockSectorRepository = growthStockSectorRepository;
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

        String csvFile = FileFactory.getFile(File.ESTIMATING_GROWTH_STOCKS);

        CSVMyReader readFile = new CSVMyReader(csvFile, growthStockSectorRepository, job);
        readFile.csvFirstSetStocks3();
        job.setGrowthStock(readFile.getSectorsGrowthStockMap());

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
        processDescriptor.setErrorMessage("estimating growth stocks failed");
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
                    String command = "xterm  -e ./file4.sh " + jobId;
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
                    command = "cmd.exe /c start /w cmd.exe /c \"echo Estimating growth stocks for job ID: " + jobId + "&& timeout 15\"";
                }*/else{
                    throw new InterruptedException();
                }

                toBeDoneInsideProcessAtBegin(pid);
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    toBeDoneInsideProcessAtEndWhenSuccess();
                } else {
                    throw new InterruptedException();
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
