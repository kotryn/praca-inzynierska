package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.web.data.Action;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataBuildingRobustPortfolioFailed;

public class StateBuildingRobustPortfolioFailed extends StateBase implements IState {
    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;

    public StateBuildingRobustPortfolioFailed(JobRepository jobRepository, ContextRepository contextRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "building_robust_portfolio_failed/"+context.getJobId();
    }

    private void saveEmptySetOfStocks(WebDataBuildingRobustPortfolioFailed input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedStocks(null);
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataBuildingRobustPortfolioFailed input = getInput(webData);
        if (input.getAction() == Action.CONTINUE) {
            //saveEmptySetOfStocks(input);
            //moveToNextStateAndSave(State.OBTAINING_PERIOD_OF_ANALYSIS, context, contextRepository);
            throw new RuntimeException("Not implemented yet");
        } else {
            throw new RuntimeException("Undefined action");
        }
    }
}