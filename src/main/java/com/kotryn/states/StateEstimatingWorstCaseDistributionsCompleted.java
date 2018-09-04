package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingWorstCaseDistributionsCompleted;

public class StateEstimatingWorstCaseDistributionsCompleted extends StateBase implements IState {

    private final ContextRepository contextRepository;

    public StateEstimatingWorstCaseDistributionsCompleted(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_distributions_completed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseDistributionsCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                moveToNextStateAndSave(State.ESTIMATING_GROWTH_STOCKS_SETUP, context, contextRepository);
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
