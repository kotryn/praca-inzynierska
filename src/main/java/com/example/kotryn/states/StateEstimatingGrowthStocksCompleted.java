package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataEstimatingGrowthStocksCompleted;

public class StateEstimatingGrowthStocksCompleted extends StateBase implements IState {
    private final ContextRepository contextRepository;

    public StateEstimatingGrowthStocksCompleted(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_growth_stocks_completed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingGrowthStocksCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_COPULA_SETUP, context, contextRepository);
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_GROWTH_STOCKS_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
