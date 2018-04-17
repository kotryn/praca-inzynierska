package com.example.kotryn.processes;

import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class ProcessFactory implements IProcessFactory {

    private final JobRepository jobRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public ProcessFactory(JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public IProcess getProcess(Long jobId) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        switch (processDescriptor.getProcessType()) {
            case SEARCHING_FOR_STOCKS:
                return new ProcessSearchingForStocks(jobId, jobRepository, processDescriptorRepository);
            default:
                throw new RuntimeException("Unknown process");
        }
    }
}
