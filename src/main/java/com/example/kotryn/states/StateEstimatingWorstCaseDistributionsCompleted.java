package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataCalculatingSampleCountCompleted;
import com.example.kotryn.web.data.WebDataEstimatingWorstCaseDistributionsCompleted;

public class StateEstimatingWorstCaseDistributionsCompleted extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingWorstCaseDistributionsCompleted(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_distributions_completed/"+context.getJobId();
    }

    private void saveSelectedEstimatingWorstCaseDistributions(WebDataEstimatingWorstCaseDistributionsCompleted input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedWorstCaseDistributions(input.getSelectedWorstCaseDistributions());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseDistributionsCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveSelectedEstimatingWorstCaseDistributions(input);
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_GROWTH_STOCKS, input.getJobId(),
                        processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_GROWTH_STOCKS_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;

            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
