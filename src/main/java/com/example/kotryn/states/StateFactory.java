package com.example.kotryn.states;

import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;

public class StateFactory  {

    public static IState getState(State state, JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        switch(state) {
            case CALCULATING_SAMPLE_COUNT_SETUP:
                return new StateCalculatingSampleCountSetup(jobRepository, contextRepository, processDescriptorRepository);
            case CALCULATING_SAMPLE_COUNT_COMPLETED:
                return new StateCalculatingSampleCountCompleted(jobRepository, contextRepository, processDescriptorRepository);
            case CALCULATING_SAMPLE_COUNT_FAILED:
                return new StateCalculatingSampleCountFailed();
            case CALCULATING_SAMPLE_COUNT_IN_PROGRESS:

                return new StateCalculatingSampleCountInProgress(contextRepository, processDescriptorRepository);
            case OBTAINING_STOCKS:
                return new StateObtainingStocks(jobRepository, contextRepository, processDescriptorRepository);

            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED:
                return new StateEstimatingWorstCaseDistributionsCompleted(contextRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_FAILED:
                return new StateEstimatingWorstCaseDistributionsFailed();
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS:
                return new StateEstimatingWorstCaseDistributionsInProgress(contextRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP:
                return new StateEstimatingWorstCaseDistributionsSetup(contextRepository, processDescriptorRepository);

            case OBTAINING_PERIOD_OF_ANALYSIS:
                return new StateObtainingPeriodOfAnalysis(jobRepository, contextRepository, processDescriptorRepository);

            case SEARCHING_FOR_STOCKS_COMPLETED:
                return new StateSearchingForStocksCompleted(jobRepository, contextRepository, processDescriptorRepository);
            case SEARCHING_FOR_STOCKS_FAILED:
                return new StateSearchingForStocksFailed(jobRepository, contextRepository);
            case SEARCHING_FOR_STOCKS_IN_PROGRESS:
                return new StateSearchingForStocksInProgress(contextRepository, processDescriptorRepository);

            case ESTIMATING_GROWTH_STOCKS_SETUP:
                return new StateEstimatingGrowthStocksSetup(jobRepository, contextRepository, processDescriptorRepository);
            case ESTIMATING_GROWTH_STOCKS_COMPLETED:
                return new StateEstimatingGrowthStocksCompleted(contextRepository);
            case ESTIMATING_GROWTH_STOCKS_FAILED:
                return new StateEstimatingGrowthStocksFailed(jobRepository, contextRepository);
            case ESTIMATING_GROWTH_STOCKS_IN_PROGRESS:
                return new StateEstimatingGrowthStocksInProgress(contextRepository, processDescriptorRepository);


            case ESTIMATING_WORST_CASE_COPULA_SETUP:
                return new StateEstimatingWorstCaseCopulaSetup(jobRepository, contextRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_COPULA_COMPLETED:
                return new StateEstimatingWorstCaseCopulaCompleted(jobRepository, contextRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_COPULA_FAILED:
                return new StateEstimatingWorstCaseCopulaFailed(jobRepository, contextRepository);
            case ESTIMATING_WORST_CASE_COPULA_IN_PROGRESS:
                return new StateEstimatingWorstCaseCopulaInProgress(contextRepository, processDescriptorRepository);

            case BUILDING_ROBUST_PORTFOLIO_SETUP:
                return new StateBuildingRobustPortfolioSetup(jobRepository, contextRepository, processDescriptorRepository);
            case BUILDING_ROBUST_PORTFOLIO_COMPLETED:
                return new StateBuildingRobustPortfolioCompleted(jobRepository, contextRepository, processDescriptorRepository);
            case BUILDING_ROBUST_PORTFOLIO_FAILED:
                return new StateBuildingRobustPortfolioFailed(jobRepository, contextRepository);
            case BUILDING_ROBUST_PORTFOLIO_IN_PROGRESS:
                return new StateBuildingRobustPortfolioInProgress(contextRepository, processDescriptorRepository);

            case CALCULATING_STATISTIC_COMPLETED:
                return new StateCalculatingStatisticCompleted(contextRepository);
            case CALCULATING_STATISTIC_FAILED:
                return new StateCalculatingStatisticFailed(jobRepository, contextRepository);
            case CALCULATING_STATISTIC_IN_PROGRESS:
                return new StateCalculatingStatisticInProgress(contextRepository, processDescriptorRepository);


            default:
                throw new RuntimeException("Unknown state");
        }
    }
}
