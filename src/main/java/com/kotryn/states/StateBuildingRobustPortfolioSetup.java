package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.Job;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataBuildingRobustPortfolioSetup;

public class StateBuildingRobustPortfolioSetup extends StateBase implements IState {
    private JobRepository jobRepository;
    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateBuildingRobustPortfolioSetup(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "building_robust_portfolio/"+context.getJobId();
    }

    private void saveBuildingRobustPortfolioSetup(WebDataBuildingRobustPortfolioSetup input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setNumberOfSamples(input.getNumberOfSamples());
        job.setYearRateOfReturn(input.getYearRateOfReturn());
        job.setToleranceLevel(input.getToleranceLevel());
        job.setMaxShare(input.getMaxShare());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataBuildingRobustPortfolioSetup input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveBuildingRobustPortfolioSetup(input);
                createProcessDescriptorAndSave(ProcessType.BUILDING_ROBUST_PORTFOLIO, input.getJobId(), processDescriptorRepository);
                moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_COPULA_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}