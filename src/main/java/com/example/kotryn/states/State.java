package com.example.kotryn.states;

public enum State {
    UNKNOWN,
    CALCULATING_SAMPLE_COUNT_COMPLETED,
    CALCULATING_SAMPLE_COUNT_FAILED,
    CALCULATING_SAMPLE_COUNT_IN_PROGRESS,
    ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED,
    ESTIMATING_WORST_CASE_DISTRIBUTIONS_FAILED,
    ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS,
    ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP,
    OBTAINING_PERIOD_OF_ANALYSIS,
    OBTAINING_RETURN_PERIOD,
    SEARCHING_FOR_STOCKS_COMPLETED,
    SEARCHING_FOR_STOCKS_FAILED,
    SEARCHING_FOR_STOCKS_IN_PROGRESS,
    OBTAINING_STOCKS,

    ESTIMATING_GROWTH_STOCKS_IN_PROGRESS,
    ESTIMATING_GROWTH_STOCKS_COMPLETED,
    ESTIMATING_GROWTH_STOCKS_FAILED,

    ESTIMATING_NON_CORRELATED_STOCKS_IN_PROGRESS,
    ESTIMATING_NON_CORRELATED_STOCKS_COMPLETED,
    ESTIMATING_NON_CORRELATED_STOCKS_FAILED,
    ESTIMATING_WORST_CASE_COPULA_IN_PROGRESS,
    ESTIMATING_WORST_CASE_COPULA_COMPLETED,
    ESTIMATING_WORST_CASE_COPULA_FAILED,
    BUILDING_ROBUST_PORTFOLIO_IN_PROGRESS,
    BUILDING_ROBUST_PORTFOLIO_COMPLETED,
    BUILDING_ROBUST_PORTFOLIO_FAILED,
    CALCULATING_STATISTIC_IN_PROGRESS,
    CALCULATING_STATISTIC_COMPLETED,
    CALCULATING_STATISTIC_FAILED
}
