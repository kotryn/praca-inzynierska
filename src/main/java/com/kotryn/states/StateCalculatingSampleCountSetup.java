package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.Job;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataCalculatingSampleCountSetup;

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
        job.setPeriodicity(input.getPeriodicity());
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
