package com.kotryn.repository;

import com.kotryn.entity.GrowthStockSector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface GrowthStockSectorRepository extends Repository<GrowthStockSector, Long> {
    GrowthStockSector save(GrowthStockSector sector);
    void flush();
    GrowthStockSector findById(Long id);
    GrowthStockSector findByJobId(Long jobId);
    void deleteById(Long id);
    @Transactional
    List<GrowthStockSector> removeByJobId(Long jobId);
    Page<GrowthStockSector> findAll(Pageable pageable);
}
