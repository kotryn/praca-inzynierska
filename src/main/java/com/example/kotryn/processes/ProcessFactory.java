package com.example.kotryn.processes;

import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.repository.*;

public class ProcessFactory implements IProcessFactory {

    private final JobRepository jobRepository;
    private final ProcessDescriptorRepository processDescriptorRepository;
    private SectorRepository sectorRepository;
    private WorstCaseDistributionSectorRepository worstCaseDistributionSectorRepository;
    private GrowthStockSectorRepository growthStockSectorRepository;

    public ProcessFactory(JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository, SectorRepository sectorRepository, WorstCaseDistributionSectorRepository worstCaseDistributionSectorRepository, GrowthStockSectorRepository growthStockSectorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.sectorRepository = sectorRepository;
        this.worstCaseDistributionSectorRepository = worstCaseDistributionSectorRepository;
        this.growthStockSectorRepository = growthStockSectorRepository;
    }

    @Override
    public IProcess getProcess(Long jobId) {
        ProcessDescriptor processDescriptor = processDescriptorRepository.getOne(jobId);
        switch (processDescriptor.getProcessType()) {
            case SEARCHING_FOR_STOCKS:
                return new ProcessSearchingForStocks(jobId, jobRepository, sectorRepository, processDescriptorRepository);
            case CALCULATING_SAMPLE_COUNT:
                return new ProcessCalculatingSampleCount(jobId, jobRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS:
                return new ProcessEstimatingWorstCaseDistributions(jobId, jobRepository, worstCaseDistributionSectorRepository, processDescriptorRepository);
            case ESTIMATING_GROWTH_STOCKS:
                return new ProcessEstimatingGrowthStocks(jobId, jobRepository, growthStockSectorRepository, processDescriptorRepository);
            case ESTIMATING_NON_CORRELATED_STOCKS:
                return new ProcessEstimatingNonCorrelatedStocks(jobId, jobRepository, processDescriptorRepository);
            case ESTIMATING_WORST_CASE_COPULA:
                return new ProcessEstimatingWorstCaseCopula(jobId, jobRepository, processDescriptorRepository);
            case BUILDING_ROBUST_PORTFOLIO:
                return new ProcessBuildingRobustPortfolio(jobId, jobRepository, processDescriptorRepository);
            case CALCULATING_STATISTIC:
                return new ProcessCalculatingStatistic(jobId, jobRepository, processDescriptorRepository);
            default:
                throw new RuntimeException("Unknown process");
        }
    }
}
