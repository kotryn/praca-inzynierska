package com.kotryn.states;

public enum State {
    UNKNOWN,

    NEW_JOB,

    OBTAINING_PERIOD_OF_ANALYSIS,
    SEARCHING_FOR_STOCKS_COMPLETED,
    SEARCHING_FOR_STOCKS_FAILED,
    SEARCHING_FOR_STOCKS_IN_PROGRESS,

    CALCULATING_SAMPLE_COUNT_SETUP,
    CALCULATING_SAMPLE_COUNT_COMPLETED,
    CALCULATING_SAMPLE_COUNT_FAILED,
    CALCULATING_SAMPLE_COUNT_IN_PROGRESS,

    ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED,
    ESTIMATING_WORST_CASE_DISTRIBUTIONS_FAILED,
    ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS,
    ESTIMATING_WORST_CASE_DISTRIBUTIONS_SETUP,

    ESTIMATING_GROWTH_STOCKS_SETUP,
    ESTIMATING_GROWTH_STOCKS_IN_PROGRESS,
    ESTIMATING_GROWTH_STOCKS_COMPLETED,
    ESTIMATING_GROWTH_STOCKS_FAILED,

    ESTIMATING_WORST_CASE_COPULA_SETUP,
    ESTIMATING_WORST_CASE_COPULA_IN_PROGRESS,
    ESTIMATING_WORST_CASE_COPULA_COMPLETED,
    ESTIMATING_WORST_CASE_COPULA_FAILED,

    BUILDING_ROBUST_PORTFOLIO_SETUP,
    BUILDING_ROBUST_PORTFOLIO_IN_PROGRESS,
    BUILDING_ROBUST_PORTFOLIO_COMPLETED,
    BUILDING_ROBUST_PORTFOLIO_FAILED,

    CALCULATING_STATISTIC_IN_PROGRESS,
    CALCULATING_STATISTIC_COMPLETED,
    CALCULATING_STATISTIC_FAILED
}
