package com.springboot.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.redis.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}