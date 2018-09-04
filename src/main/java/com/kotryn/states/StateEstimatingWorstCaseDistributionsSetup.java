package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingWorstCaseDistributionsSetup;

public class StateEstimatingWorstCaseDistributionsSetup extends StateBase implements IState {

    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingWorstCaseDistributionsSetup(ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_worst_case_distributions/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseDistributionsSetup input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_WORST_CASE_DISTRIBUTIONS, input.getJobId(),
                        processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}