package com.example.kotryn.processes;

import com.example.kotryn.entity.Process.ProcessDescriptor;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class ProcessFactory implements IProcessFactory {

    private final ProcessDescriptorRepository processDescriptorRepository;

    public ProcessFactory(ProcessDescriptorRepository processDescriptorRepository) {
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public IProcess getProcess(long jobId) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        switch (processDescriptor.getProcessType()) {
            case SEARCHING_FOR_STOCKS:
                return new ProcessSearchingForStocks(jobId);
            default:
                throw new RuntimeException("Unknown process");
        }
    }
}
