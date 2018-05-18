package com.example.kotryn.repository;

import com.example.kotryn.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface StockRepository extends Repository<Stock, Long> {
    Stock save(Stock stock);
    Stock findById(Long id);
    Stock findByJobId(Long jobId);
    void deleteById(Long id);
    Page<Stock> findAll(Pageable pageable);
}
