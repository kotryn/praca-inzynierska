package com.example.kotryn.processes;

import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.repository.StockRepository;

public class ProcessFactory implements IProcessFactory {

    private final JobRepository jobRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;
    private StockRepository stockRepository;

    public ProcessFactory(JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository, StockRepository stockRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public IProcess getProcess(Long jobId) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        switch (processDescriptor.getProcessType()) {
            case SEARCHING_FOR_STOCKS:
                return new ProcessSearchingForStocks(jobId, jobRepository, stockRepository, processDescriptorRepository);
            case CALCULATING_SAMPLE_COUNT:
                return new ProcessCalculatingSampleCount(jobId, jobRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS:
                return new ProcessEstimatingWorstCaseDistributions(jobId, jobRepository, processDescriptorRepository);
            case ESTIMATING_GROWTH_STOCKS:
                return new ProcessEstimatingGrowthStocks(jobId, jobRepository, processDescriptorRepository);
            case ESTIMATING_NON_CORRELATED_STOCKS:
                return new ProcessEstimatingNonCorrelatedStocks(jobId, jobRepository, stockRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_COPULA:
                return new ProcessEstimatingWorstCaseCopula(jobId, jobRepository, stockRepository, processDescriptorRepository);
            case BUILDING_ROBUST_PORTFOLIO:
                return new ProcessBuildingRobustPortfolio(jobId, jobRepository, stockRepository, processDescriptorRepository);
            case CALCULATING_STATISTIC:
                return new ProcessCalculatingStatistic(jobId, jobRepository, stockRepository, processDescriptorRepository);
            default:
                throw new RuntimeException("Unknown process");
        }
    }
}
