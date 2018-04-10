package com.example.kotryn.states;

import com.example.kotryn.entity.Context.Context;
import com.example.kotryn.entity.Process.ProcessDescriptor;
import com.example.kotryn.processes.*;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

class StateBase {

    void moveToNextStateAndSave(State state, Context context, ContextRepository contextRepository) {
        context.setState(state);
        contextRepository.saveAndFlush(context);
    }

    void createProcessDescriptorAndSave(ProcessType processType, long jobId,
                                        ProcessDescriptorRepository processDescriptorRepository) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        processDescriptor.setProcessType(processType);
        processDescriptor.setProcessState(ProcessState.STARTING_UP);
        processDescriptorRepository.saveAndFlush(processDescriptor);
    }

    void startProcess(long jobId) {
        IProcessFactory processFactory = AbstractProcessFactory.getFactory();
        IProcess process = processFactory.getProcess(jobId);
        process.start();
    }

    void verifyProcessType(ProcessType processType, long jobId,
                           ProcessDescriptorRepository processDescriptorRepository) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        if (processDescriptor.getProcessType() != processType) {
            throw new RuntimeException("Incorrect process running");
        }
    }
}
