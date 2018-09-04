package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.processes.ProcessState;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingWorstCaseDistributionsInProgress;

public class StateEstimatingWorstCaseDistributionsInProgress extends StateBase implements IState {
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingWorstCaseDistributionsInProgress(ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_distributions_in_progress/"+context.getJobId();
    }

    private void ifCalculatingDoneMoveToNextStateAndSave(Context context) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(context.getJobId());
        if (processDescriptor.getProcessState() == ProcessState.COMPLETED_SUCCESS) {
            moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED, context, contextRepository);
        } else if (processDescriptor.getProcessState() == ProcessState.COMPLETED_FAILURE){
            moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_FAILED, context, contextRepository);
        }
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseDistributionsInProgress input = getInput(webData);
        switch (input.getAction()) {
            case INTERRUPT:
                interruptProcess(input.getJobId());
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP, context, contextRepository);
                break;
            case REFRESH:
                verifyProcessType(ProcessType.ESTIMATING_WORST_CASE_DISTRIBUTIONS, input.getJobId(), processDescriptorRepository);
                ifCalculatingDoneMoveToNextStateAndSave(context);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
