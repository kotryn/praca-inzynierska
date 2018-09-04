package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataCalculatingSampleCountCompleted;

public class StateCalculatingSampleCountCompleted extends StateBase implements IState {

    private final ContextRepository contextRepository;

    public StateCalculatingSampleCountCompleted(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "calculating_sample_count_completed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataCalculatingSampleCountCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP, context, contextRepository);
                break;

            case PREVIOUS:
                moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
