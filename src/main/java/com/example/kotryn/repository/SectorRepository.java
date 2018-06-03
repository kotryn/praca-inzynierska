package com.example.kotryn.repository;

import com.example.kotryn.entity.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface SectorRepository extends Repository<Sector, Long> {
    Sector save(Sector sector);
    Sector findById(Long id);
    Sector findByJobId(Long jobId);
    void deleteById(Long id);
    Page<Sector> findAll(Pageable pageable);
}
