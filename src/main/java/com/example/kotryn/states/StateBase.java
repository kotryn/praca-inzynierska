package com.example.kotryn.states;

import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.processes.*;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;

class StateBase {

    @SuppressWarnings("unchecked")
    <T> T getInput(IWebData webData) {
        try {
            return (T) webData;
        } catch (ClassCastException e) {
            throw new RuntimeException("Unknown webData");
        }
    }

    void moveToNextStateAndSave(State state, Context context, ContextRepository contextRepository) {
        context.setState(state);
        contextRepository.saveAndFlush(context);
    }

    void createProcessDescriptorAndSave(ProcessType processType, Long jobId,
                                        ProcessDescriptorRepository processDescriptorRepository) {

        ProcessDescriptor processDescriptor = processDescriptorRepository.findOne(jobId);

        processDescriptor.setProcessType(processType);
        processDescriptor.setProcessState(ProcessState.STARTING_UP);
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    void startProcess(Long jobId) {
        IProcessFactory processFactory = AbstractProcessFactory.getFactory();
        IProcess process = processFactory.getProcess(jobId);
        process.start();
    }

    void verifyProcessType(ProcessType processType, Long jobId,
                           ProcessDescriptorRepository processDescriptorRepository) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        if (processDescriptor.getProcessType() != processType) {
            throw new RuntimeException("Incorrect process running");
        }
    }
}
