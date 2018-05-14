package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;

public class StateEstimatingWorstCaseDistributionsSetup extends StateBase implements IState {

    private JobRepository jobRepository;
    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingWorstCaseDistributionsSetup(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_distributions/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " not yet implemented");
    }
}
