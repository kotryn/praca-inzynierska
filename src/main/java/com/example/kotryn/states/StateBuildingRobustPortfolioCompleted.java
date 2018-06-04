package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataBuildingRobustPortfolioCompleted;

public class StateBuildingRobustPortfolioCompleted extends StateBase implements IState {

    private final JobRepository jobRepository;
    private final ContextRepository contextRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;

    public StateBuildingRobustPortfolioCompleted(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }


    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "building_robust_portfolio_completed/"+context.getJobId();
    }

    private void saveBuildingRobustPortfolio(WebDataBuildingRobustPortfolioCompleted input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedRobustPortfolio(input.getSelectedRobustPortfolio());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataBuildingRobustPortfolioCompleted input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveBuildingRobustPortfolio(input);
                createProcessDescriptorAndSave(ProcessType.CALCULATING_STATISTIC, input.getJobId(),
                       processDescriptorRepository);
                moveToNextStateAndSave(State.CALCULATING_STATISTIC_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.BUILDING_ROBUST_PORTFOLIO_SETUP, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
