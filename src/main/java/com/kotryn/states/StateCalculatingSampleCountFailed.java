package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.Action;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataCalculatingSampleCountFailed;

public class StateCalculatingSampleCountFailed extends StateBase implements IState {

    private final ContextRepository contextRepository;

    public StateCalculatingSampleCountFailed(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "calculating_sample_count_failed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataCalculatingSampleCountFailed input = getInput(webData);
        if (input.getAction() == Action.CONTINUE) {
            moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_SETUP, context, contextRepository);
        }  else {
            throw new RuntimeException("Undefined action");
        }
    }
}

