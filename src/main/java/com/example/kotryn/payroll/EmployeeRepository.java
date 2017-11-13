package com.example.kotryn.payroll;

import com.example.kotryn.payroll.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}