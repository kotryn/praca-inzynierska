package com.example.kotryn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kotryn.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
