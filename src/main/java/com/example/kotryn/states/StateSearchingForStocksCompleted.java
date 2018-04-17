package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataSearchingForStocksCompleted;

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
            case PREVIOUS:
                saveSelectedStocks(input);
                moveToNextStateAndSave(State.OBTAINING_PERIOD_OF_ANALYSIS, context, contextRepository);
                break;
            case NEXT:
                saveSelectedStocks(input);
                moveToNextStateAndSave(State.OBTAINING_RETURN_PERIOD, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
