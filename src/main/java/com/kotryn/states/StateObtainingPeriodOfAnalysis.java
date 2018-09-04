package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.Job;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataObtainingPeriodOfAnalysis;
import org.springframework.stereotype.Service;

@Service
public class StateObtainingPeriodOfAnalysis extends StateBase implements IState {

    private JobRepository jobRepository;
    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateObtainingPeriodOfAnalysis(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "period_of_analysis/"+context.getJobId();
    }

    private void savePeriodOfAnalysis(WebDataObtainingPeriodOfAnalysis input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setStartDate(input.getStartDate());
        job.setEndDate(input.getEndDate());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataObtainingPeriodOfAnalysis input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                savePeriodOfAnalysis(input);
                createProcessDescriptorAndSave(ProcessType.SEARCHING_FOR_STOCKS, input.getJobId(),
                        processDescriptorRepository);
                // always set the state first
                moveToNextStateAndSave(State.SEARCHING_FOR_STOCKS_IN_PROGRESS, context, contextRepository);
                // then start the process
                // (launching should be done in a separate thread so as not to block UI)
                startProcess(input.getJobId());
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
