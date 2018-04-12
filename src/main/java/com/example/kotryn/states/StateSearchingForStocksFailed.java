package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context.Context;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.web.data.Action;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataSearchingForStocksFailed;

public class StateSearchingForStocksFailed extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;

    public StateSearchingForStocksFailed(JobRepository jobRepository, ContextRepository contextRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        controller.searchingForStocksFailedGET(context.getJobId());
        return null;
    }

    private void saveEmptySetOfStocks(WebDataSearchingForStocksFailed input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedStocks(null);
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataSearchingForStocksFailed input = getInput(webData);
        if (input.getAction() == Action.CONTINUE) {
            saveEmptySetOfStocks(input);
            moveToNextStateAndSave(State.OBTAINING_PERIOD_OF_ANALYSIS, context, contextRepository);
        } else {
            throw new RuntimeException("Undefined action");
        }
    }
}
