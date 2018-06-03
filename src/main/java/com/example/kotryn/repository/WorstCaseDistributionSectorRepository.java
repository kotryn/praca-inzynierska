package com.example.kotryn.repository;

import com.example.kotryn.entity.WorstCaseDistributionSector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface WorstCaseDistributionSectorRepository extends Repository<WorstCaseDistributionSector, Long> {
    WorstCaseDistributionSector save(WorstCaseDistributionSector sector);
    WorstCaseDistributionSector findById(Long id);
    WorstCaseDistributionSector findByJobId(Long jobId);
    void deleteById(Long id);
    Page<WorstCaseDistributionSector> findAll(Pageable pageable);
}
