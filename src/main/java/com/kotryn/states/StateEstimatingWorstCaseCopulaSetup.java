package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.Job;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingWorstCaseCopulaSetup;

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