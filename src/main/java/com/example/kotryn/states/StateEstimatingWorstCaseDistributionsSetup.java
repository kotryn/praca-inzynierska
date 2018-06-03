package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataEstimatingWorstCaseDistributionsSetup;

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

    private void saveEstimatingWorstCaseDistributions(WebDataEstimatingWorstCaseDistributionsSetup input) {
        Job job = jobRepository.getOne(input.getJobId());
        /*job.setStartDate(input.getStartDate());
        job.setEndDate(input.getEndDate());*/
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingWorstCaseDistributionsSetup input = getInput(webData);//nie pasuje mu
        switch (input.getAction()) {
            case NEXT:
                //saveEstimatingWorstCaseDistributions(input);
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_WORST_CASE_DISTRIBUTIONS, input.getJobId(),
                        processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                //saveSelectedStocks(input);
                moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}