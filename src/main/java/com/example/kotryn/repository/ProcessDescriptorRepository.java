package com.example.kotryn.repository;

import com.example.kotryn.entity.ProcessDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessDescriptorRepository extends JpaRepository<ProcessDescriptor, Long> {
}
