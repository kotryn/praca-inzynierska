package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataEstimatingGrowthStocksCompleted;

public class StateEstimatingGrowthStocksCompleted extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingGrowthStocksCompleted(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_growth_stocks_completed/"+context.getJobId();
    }

    private void saveSelectedEstimatingGrowthStocks(WebDataEstimatingGrowthStocksCompleted input) {
        Job job = jobRepository.getOne(input.getJobId());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingGrowthStocksCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                //saveSelectedEstimatingGrowthStocks(input);
                //createProcessDescriptorAndSave(ProcessType.ESTIMATING_WORST_CASE_DISTRIBUTIONS, input.getJobId(),
                //       processDescriptorRepository);
                //moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP, context, contextRepository);
                //startProcess(input.getJobId());
                //break;
                throw new RuntimeException("Not implemented yet");
            case PREVIOUS:
                //moveToNextStateAndSave(State.SEARCHING_FOR_STOCKS_COMPLETED, context, contextRepository);
                //break;
                throw new RuntimeException("Not implemented yet");
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
