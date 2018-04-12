package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context.Context;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.Action;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataObtainingPeriodOfAnalysis;
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
        //controller.obtainingPeriodOfAnalysisGET(context.getJobId());
        return "period_of_analysis/"+context.getJobId();
    }

    //all repository -> null
    private void savePeriodOfAnalysis(WebDataObtainingPeriodOfAnalysis input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setStartDate(input.getStartDate());
        job.setEndDate(input.getEndDate());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataObtainingPeriodOfAnalysis input = getInput(webData);
        if (input.getAction() == Action.NEXT) {
            savePeriodOfAnalysis(input);
            createProcessDescriptorAndSave(ProcessType.SEARCHING_FOR_STOCKS, input.getJobId(),
                    processDescriptorRepository);
            // always set the state first
            moveToNextStateAndSave(State.SEARCHING_FOR_STOCKS_IN_PROGRESS, context, contextRepository);
            // then start the process
            // (launching should be done in a separate thread so as not to block UI)
            startProcess(input.getJobId());
        } else {
            throw new RuntimeException("Undefined action");
        }
    }
}
