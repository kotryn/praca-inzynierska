package com.example.kotryn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kotryn.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
}
