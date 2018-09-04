package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.Action;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingWorstCaseCopulaFailed;

public class  StateEstimatingWorstCaseCopulaFailed extends StateBase implements IState {
    private final ContextRepository contextRepository;

    public StateEstimatingWorstCaseCopulaFailed(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_copula_failed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseCopulaFailed input = getInput(webData);
        if (input.getAction() == Action.CONTINUE) {
            moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_COPULA_SETUP, context, contextRepository);
        } else {
            throw new RuntimeException("Undefined action");
        }
    }
}
