package com.example.kotryn.states;

import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class StateFactory  {

    public static IState getState(State state, JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        switch(state) {
            case CALCULATING_SAMPLE_COUNT_COMPLETED:
                return new StateCalculatingSampleCountCompleted(jobRepository, contextRepository, processDescriptorRepository);
            case CALCULATING_SAMPLE_COUNT_FAILED:
                return new StateCalculatingSampleCountFailed();
            case CALCULATING_SAMPLE_COUNT_IN_PROGRESS:
                return new StateCalculatingSampleCountInProgress(contextRepository, processDescriptorRepository);
            case OBTAINING_STOCKS:
                return new StateObtainingStocks(jobRepository, contextRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED:
                return new StateEstimatingWorstCaseDistributionsCompleted(jobRepository, contextRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_FAILED:
                return new StateEstimatingWorstCaseDistributionsFailed();
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS:
                return new StateEstimatingWorstCaseDistributionsInProgress(contextRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP:
                return new StateEstimatingWorstCaseDistributionsSetup(jobRepository, contextRepository, processDescriptorRepository);
            case OBTAINING_PERIOD_OF_ANALYSIS:
                return new StateObtainingPeriodOfAnalysis(jobRepository, contextRepository, processDescriptorRepository);
            case SEARCHING_FOR_STOCKS_COMPLETED:
                return new StateSearchingForStocksCompleted(jobRepository, contextRepository, processDescriptorRepository);
            case SEARCHING_FOR_STOCKS_FAILED:
                return new StateSearchingForStocksFailed(jobRepository, contextRepository);
            case SEARCHING_FOR_STOCKS_IN_PROGRESS:
                return new StateSearchingForStocksInProgress(contextRepository, processDescriptorRepository);
            default:
                throw new RuntimeException("Unknown state");
        }
    }
}
