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

public class StateCalculatingSampleCountCompleted extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateCalculatingSampleCountCompleted(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "calculating_sample_count_completed/"+context.getJobId();
    }

    private void saveSelectedCalculatingSample(WebDataCalculatingSampleCountCompleted input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedCalculatingSample(input.getSelectedCalculatingSample());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataCalculatingSampleCountCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveSelectedCalculatingSample(input);
                //createProcessDescriptorAndSave(ProcessType.ESTIMATING_WORST_CASE_DISTRIBUTIONS, input.getJobId(),
                 //       processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP, context, contextRepository);
                //startProcess(input.getJobId());
                break;

            case PREVIOUS:
                moveToNextStateAndSave(State.SEARCHING_FOR_STOCKS_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
