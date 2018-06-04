package com.example.kotryn.repository;

import com.example.kotryn.entity.GrowthStockSector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface GrowthStockSectorRepository extends Repository<GrowthStockSector, Long> {
    GrowthStockSector save(GrowthStockSector sector);
    GrowthStockSector findById(Long id);
    GrowthStockSector findByJobId(Long jobId);
    void deleteById(Long id);
    Page<GrowthStockSector> findAll(Pageable pageable);
}
