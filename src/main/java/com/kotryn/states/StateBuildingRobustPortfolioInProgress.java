package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.ProcessDescriptor;
import com.kotryn.processes.ProcessState;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataBuildingRobustPortfolioInProgress;

public class  StateBuildingRobustPortfolioInProgress extends StateBase implements IState {
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateBuildingRobustPortfolioInProgress(ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "building_robust_portfolio_in_progress/"+context.getJobId();
    }

    private void ifSearchingDoneMoveToNextStateAndSave(Context context) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(context.getJobId());
        if (processDescriptor.getProcessState() == ProcessState.COMPLETED_SUCCESS) {
            moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_COMPLETED, context, contextRepository);
        } else if (processDescriptor.getProcessState() == ProcessState.COMPLETED_FAILURE){
            moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_FAILED, context, contextRepository);
        }
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataBuildingRobustPortfolioInProgress input = getInput(webData);
        switch (input.getAction()) {
            case INTERRUPT:
                interruptProcess(input.getJobId());
                moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_SETUP, context, contextRepository);
                break;
            case REFRESH:
                verifyProcessType(ProcessType.BUILDING_ROBUST_PORTFOLIO, input.getJobId(), processDescriptorRepository);
                ifSearchingDoneMoveToNextStateAndSave(context);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
