package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataEstimatingNonCorrelatedStocksCompleted;

public class StateEstimatingNonCorrelatedStocksCompleted extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingNonCorrelatedStocksCompleted(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_non_correlated_stocks_completed/"+context.getJobId();
    }

    private void saveSelectedEstimatingNonCorrelatedStocks(WebDataEstimatingNonCorrelatedStocksCompleted input) {
        Job job = jobRepository.getOne(input.getJobId());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingNonCorrelatedStocksCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                //saveSelectedEstimatingNonCorrelatedStocks(input);
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_WORST_CASE_COPULA, input.getJobId(),
                       processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_COPULA_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_GROWTH_STOCKS_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}