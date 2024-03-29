package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.entity.Job;
import com.kotryn.processes.ProcessType;
import com.kotryn.repository.ContextRepository;
import com.kotryn.repository.JobRepository;
import com.kotryn.repository.ProcessDescriptorRepository;
import com.kotryn.web.data.IWebData;
import com.kotryn.web.data.WebDataEstimatingGrowthStocksSetup;

public class StateEstimatingGrowthStocksSetup extends StateBase implements IState {
    private JobRepository jobRepository;
    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingGrowthStocksSetup(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_growth_stocks/"+context.getJobId();
    }

    private void saveEstimatingGrowthStocksSetup(WebDataEstimatingGrowthStocksSetup input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setMaxNumberSector(input.getMaxNumberSector());
        job.setMaxNumberIndustry(input.getMaxNumberIndustry());
        job.setMaxCoefficient(input.getMaxCoefficient());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingGrowthStocksSetup input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveEstimatingGrowthStocksSetup(input);
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_GROWTH_STOCKS, input.getJobId(), processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_GROWTH_STOCKS_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
