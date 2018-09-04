package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.Action;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingGrowthStocksFailed;

public class StateEstimatingGrowthStocksFailed extends StateBase implements IState {
    private final ContextRepository contextRepository;

    public StateEstimatingGrowthStocksFailed(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_growth_stocks_failed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingGrowthStocksFailed input = getInput(webData);
        if (input.getAction() == Action.CONTINUE) {
            moveToNextStateAndSave(State.ESTIMATING_GROWTH_STOCKS_SETUP, context, contextRepository);
        } else {
            throw new RuntimeException("Undefined action");
        }
    }
}
