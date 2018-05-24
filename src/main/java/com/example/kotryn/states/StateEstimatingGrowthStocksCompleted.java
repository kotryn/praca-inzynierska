package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
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
        job.setSelectedGrowthStocks(input.getSelectedGrowthStocks());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingGrowthStocksCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveSelectedEstimatingGrowthStocks(input);
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_NON_CORRELATED_STOCKS, input.getJobId(),
                       processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_NON_CORRELATED_STOCKS_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
