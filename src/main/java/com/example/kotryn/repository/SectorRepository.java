package com.example.kotryn.repository;

import com.example.kotryn.entity.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface SectorRepository extends Repository<Sector, Long> {
    Sector save(Sector sector);
    void flush();
    Sector findById(Long id);
    Sector findByJobId(Long jobId);
    void deleteById(Long id);

    @Transactional
    List<Sector> removeByJobId(Long jobId);
    Page<Sector> findAll(Pageable pageable);
}
