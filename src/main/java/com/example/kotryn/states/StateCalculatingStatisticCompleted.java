package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataCalculatingStatisticCompleted;

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