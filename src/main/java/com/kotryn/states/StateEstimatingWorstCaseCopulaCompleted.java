package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingWorstCaseCopulaCompleted;

public class StateEstimatingWorstCaseCopulaCompleted extends StateBase implements IState {

    private final ContextRepository contextRepository;

    public StateEstimatingWorstCaseCopulaCompleted(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_copula_completed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseCopulaCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_SETUP, context, contextRepository);
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_COPULA_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
