package com.example.kotryn.states;

import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class StateFactory  {

    //private static JobRepository jobRepository;
    //private static ContextRepository contextRepository;
    //private static ProcessDescriptorRepository processDescriptorRepository;


    public static IState getState(State state, JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        switch(state) {
            case CALCULATING_SAMPLE_COUNT_COMPLETED:
                return new StateCalculatingSampleCountCompleted();
            case CALCULATING_SAMPLE_COUNT_FAILED:
                return new StateCalculatingSampleCountFailed();
            case CALCULATING_SAMPLE_COUNT_IN_PROGRESS:
                return new StateCalculatingSampleCountInProgress();
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED:
                return new StateEstimatingWorstCaseDistributionsCompleted();
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_FAILED:
                return new StateEstimatingWorstCaseDistributionsFailed();
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS:
                return new StateEstimatingWorstCaseDistributionsInProgress();
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP:
                return new StateEstimatingWorstCaseDistributionsSetup();
            case OBTAINING_PERIOD_OF_ANALYSIS:
                return new StateObtainingPeriodOfAnalysis(jobRepository, contextRepository, processDescriptorRepository);
            case OBTAINING_RETURN_PERIOD:
                return new StateObtainingReturnPeriod();
            case SEARCHING_FOR_STOCKS_COMPLETED:
                return new StateSearchingForStocksCompleted(jobRepository, contextRepository);
            case SEARCHING_FOR_STOCKS_FAILED:
                return new StateSearchingForStocksFailed(jobRepository, contextRepository);
            case SEARCHING_FOR_STOCKS_IN_PROGRESS:
                return new StateSearchingForStocksInProgress(contextRepository, processDescriptorRepository);
            default:
                throw new RuntimeException("Unknown state");
        }
    }
}
