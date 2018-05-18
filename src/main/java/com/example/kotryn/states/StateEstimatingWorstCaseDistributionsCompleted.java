package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
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

    private void saveSelectedCalculatingSample(WebDataEstimatingWorstCaseDistributionsCompleted input) {
        Job job = jobRepository.getOne(input.getJobId());
        //job.setSelectedCalculatingSample(input.getSelectedCalculatingSample());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseDistributionsCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveSelectedCalculatingSample(input);
                //createProcessDescriptorAndSave(ProcessType.ESTIMATING_WORST_CASE_DISTRIBUTIONS, input.getJobId(),
                //       processDescriptorRepository);
                moveToNextStateAndSave(State.OBTAINING_STOCKS, context, contextRepository);
                //startProcess(input.getJobId());
                break;

            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
