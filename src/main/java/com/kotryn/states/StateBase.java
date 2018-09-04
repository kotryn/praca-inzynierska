package com.kotryn.states;

import com.kotryn.entity.Context;
import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.processes.*;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;

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

    void interruptProcess(Long jobId) {
        IProcessFactory processFactory = AbstractProcessFactory.getFactory();
        IProcess process = processFactory.getProcess(jobId);
        process.interrupt();
    }

    void verifyProcessType(ProcessType processType, Long jobId,
                           ProcessDescriptorRepository processDescriptorRepository) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        if (processDescriptor.getProcessType() != processType) {
            throw new RuntimeException("Incorrect process running");
        }
    }
}
