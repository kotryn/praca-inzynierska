package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.processes.ProcessState;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataCalculatingStatisticInProgress;

public class StateCalculatingStatisticInProgress extends StateBase implements IState {
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateCalculatingStatisticInProgress(ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "calculating_statistic_in_progress/"+context.getJobId();
    }

    private void ifSearchingDoneMoveToNextStateAndSave(Context context) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(context.getJobId());
        if (processDescriptor.getProcessState() == ProcessState.COMPLETED_SUCCESS) {
            moveToNextStateAndSave(State.CALCULATING_STATISTIC_COMPLETED, context, contextRepository);
        } else if (processDescriptor.getProcessState() == ProcessState.COMPLETED_FAILURE){
            moveToNextStateAndSave(State.CALCULATING_STATISTIC_FAILED, context, contextRepository);
        }
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataCalculatingStatisticInProgress input = getInput(webData);
        switch (input.getAction()) {
            case INTERRUPT:
                interruptProcess(input.getJobId());
                moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_COMPLETED, context, contextRepository);
                break;
            case REFRESH:
                verifyProcessType(ProcessType.CALCULATING_STATISTIC, input.getJobId(), processDescriptorRepository);
                ifSearchingDoneMoveToNextStateAndSave(context);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
