package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataCalculatingStatisticCompleted;

public class StateCalculatingStatisticCompleted extends StateBase implements IState {

    private final ContextRepository contextRepository;

    public StateCalculatingStatisticCompleted(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "calculating_statistic_completed/" + context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataCalculatingStatisticCompleted input = getInput(webData);
        switch (input.getAction()) {
            case PREVIOUS:
                moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}