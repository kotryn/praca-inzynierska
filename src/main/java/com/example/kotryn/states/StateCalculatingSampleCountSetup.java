package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataCalculatingSampleCountSetup;

public class StateCalculatingSampleCountSetup extends StateBase implements IState {

    private JobRepository jobRepository;
    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateCalculatingSampleCountSetup(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "calculating_sample_count_setup/"+context.getJobId();
    }

    private void saveCalculatingSampleCountSetup(WebDataCalculatingSampleCountSetup input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setStartInSampleDate(input.getStartInSampleDate());
        job.setEndInSampleDate(input.getEndInSampleDate());
        job.setStartOutOfSampleDate(input.getStartOutOfSampleDate());
        job.setEndOutOfSampleDate(input.getEndOutOfSampleDate());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataCalculatingSampleCountSetup input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveCalculatingSampleCountSetup(input);
                createProcessDescriptorAndSave(ProcessType.CALCULATING_SAMPLE_COUNT, input.getJobId(),
                        processDescriptorRepository);
                moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.SEARCHING_FOR_STOCKS_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
