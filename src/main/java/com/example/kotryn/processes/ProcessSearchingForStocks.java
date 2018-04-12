package com.example.kotryn.processes;

import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.entity.Process.ProcessDescriptor;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class ProcessSearchingForStocks implements IProcess {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private final Long jobId;

    public ProcessSearchingForStocks(Long jobId, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobId = jobId;
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    public void toBeDoneInsideProcessAtBegin() {
        // update processDescriptorRepository
        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);
        processDescriptor.setProcessState(ProcessState.IN_PROGRESS);
        processDescriptor.setPid(1234);
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    public void toBeDoneInsideProcessAtEndWhenSuccess() {
        // update jobRepository
        Job job = jobRepository.findOne(jobId);
        job.setAvailableStocks(Arrays.asList("Microsoft Corporation (MSFT)",
                "International Business Machines Corporation (IBM)",
                "Oracle Corporation (ORCL)"));
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
                processDescriptor.setHost("localhost");
                //processDescriptor.setSystemType(SystemType.WINDOWS);
                //String command = "cmd.exe /c start /w cmd.exe /c \"echo Searching for stocks for job ID: "
                //        + jobId + "&& timeout 15\"";
                processDescriptor.setSystemType(SystemType.LINUX);
                String command = "xterm  -e ./file.sh " + jobId;
                Process process = Runtime.getRuntime().exec(command);
                toBeDoneInsideProcessAtBegin();
                int exitCode = process.waitFor();
                // here the process is finished
                // (closing window finishes the process with exitCode == 0)
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
        // interrupting should be done in a separate thread so as not to block UI
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
