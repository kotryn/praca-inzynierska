package com.example.kotryn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kotryn.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}