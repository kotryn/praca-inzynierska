package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.web.data.Action;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataEstimatingNonCorrelatedStocksFailed;

public class StateEstimatingNonCorrelatedStocksFailed extends StateBase implements IState {
    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;

    public StateEstimatingNonCorrelatedStocksFailed(JobRepository jobRepository, ContextRepository contextRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_non_correlated_stocks_failed/"+context.getJobId();
    }

    private void saveEmptySetOfStocks(WebDataEstimatingNonCorrelatedStocksFailed input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedStocks(null);
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingNonCorrelatedStocksFailed input = getInput(webData);
        if (input.getAction() == Action.CONTINUE) {
            //saveEmptySetOfStocks(input);
            //moveToNextStateAndSave(State.OBTAINING_PERIOD_OF_ANALYSIS, context, contextRepository);
            throw new RuntimeException("Not implemented yet");
        } else {
            throw new RuntimeException("Undefined action");
        }
    }
}
