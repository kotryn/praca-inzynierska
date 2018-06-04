package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataEstimatingGrowthStocksSetup;
import com.example.kotryn.web.data.WebDataEstimatingWorstCaseCopulaSetup;

public class StateEstimatingWorstCaseCopulaSetup extends StateBase implements IState {
    private JobRepository jobRepository;
    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingWorstCaseCopulaSetup(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_copula/"+context.getJobId();
    }

    private void saveEstimatingWorstCaseCopulaSetup(WebDataEstimatingWorstCaseCopulaSetup input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setCopulaWindowSize(input.getCopulaWindowSize());
        job.setCopulaType(input.getCopulaType());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseCopulaSetup input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveEstimatingWorstCaseCopulaSetup(input);
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_WORST_CASE_COPULA, input.getJobId(), processDescriptorRepository);
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