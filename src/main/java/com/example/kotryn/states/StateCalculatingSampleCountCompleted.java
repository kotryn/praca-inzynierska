package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataCalculatingSampleCountCompleted;

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
