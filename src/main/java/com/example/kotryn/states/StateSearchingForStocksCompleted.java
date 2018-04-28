package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataSearchingForStocksCompleted;

public class StateSearchingForStocksCompleted extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateSearchingForStocksCompleted(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
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
                createProcessDescriptorAndSave(ProcessType.CALCULATING_SAMPLE_COUNT, input.getJobId(),
                        processDescriptorRepository);
                moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                //saveSelectedStocks(input);
                //moveToNextStateAndSave(State.OBTAINING_RETURN_PERIOD, context, contextRepository);
                //break;
                throw new RuntimeException("TEST");
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
