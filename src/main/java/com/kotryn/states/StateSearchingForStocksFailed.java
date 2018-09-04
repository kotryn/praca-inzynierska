package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.Job;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.web.data.Action;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataSearchingForStocksFailed;

public class StateSearchingForStocksFailed extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;

    public StateSearchingForStocksFailed(JobRepository jobRepository, ContextRepository contextRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "stocks_search_failed/"+context.getJobId();
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
