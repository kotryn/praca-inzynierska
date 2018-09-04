package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.repository.ContextRepository;
import com.kotryn.web.data.Action;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataBuildingRobustPortfolioFailed;

public class StateBuildingRobustPortfolioFailed extends StateBase implements IState {
    private final ContextRepository contextRepository;

    public StateBuildingRobustPortfolioFailed(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "building_robust_portfolio_failed/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataBuildingRobustPortfolioFailed input = getInput(webData);
        if (input.getAction() == Action.CONTINUE) {
            moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_SETUP, context, contextRepository);
        } else {
            throw new RuntimeException("Undefined action");
        }
    }
}
