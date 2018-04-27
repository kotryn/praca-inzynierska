package com.example.kotryn.processes;

import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.repository.StockRepository;

public class ProcessFactory implements IProcessFactory {

    private final JobRepository jobRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;
    private StockRepository stockRepository;

    public ProcessFactory(JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository, StockRepository stockRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public IProcess getProcess(Long jobId) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        switch (processDescriptor.getProcessType()) {
            case SEARCHING_FOR_STOCKS:
                return new ProcessSearchingForStocks(jobId, jobRepository, stockRepository, processDescriptorRepository);
            case CALCULATING_SAMPLE_COUNT:
                return new ProcessCalculatingSampleCount(jobId, jobRepository, stockRepository, processDescriptorRepository);
            default:
                throw new RuntimeException("Unknown process");
        }
    }
}
