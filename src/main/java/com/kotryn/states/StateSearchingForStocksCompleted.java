package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.Job;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataSearchingForStocksCompleted;

public class StateSearchingForStocksCompleted extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;

    public StateSearchingForStocksCompleted(JobRepository jobRepository, ContextRepository contextRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "stocks_search_completed/"+context.getJobId();
    }

    private void saveSelectedStocks(WebDataSearchingForStocksCompleted input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedStocks(input.getSelectedStocks());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataSearchingForStocksCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveSelectedStocks(input);
                moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
