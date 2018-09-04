package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataBuildingRobustPortfolioCompleted;

public class StateBuildingRobustPortfolioCompleted extends StateBase implements IState {

    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateBuildingRobustPortfolioCompleted(ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "building_robust_portfolio_completed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataBuildingRobustPortfolioCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                createProcessDescriptorAndSave(ProcessType.CALCULATING_STATISTIC, input.getJobId(),
                       processDescriptorRepository);
                moveToNextStateAndSave(State.CALCULATING_STATISTIC_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
