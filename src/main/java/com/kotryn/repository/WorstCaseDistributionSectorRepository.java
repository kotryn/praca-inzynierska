package com.kotryn.repository;

import com.kotryn.entity.WorstCaseDistributionSector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface WorstCaseDistributionSectorRepository extends Repository<WorstCaseDistributionSector, Long> {
    WorstCaseDistributionSector save(WorstCaseDistributionSector sector);
    void flush();
    WorstCaseDistributionSector findById(Long id);
    WorstCaseDistributionSector findByJobId(Long jobId);
    void deleteById(Long id);
    @Transactional
    List<WorstCaseDistributionSector> removeByJobId(Long jobId);
    Page<WorstCaseDistributionSector> findAll(Pageable pageable);
}
