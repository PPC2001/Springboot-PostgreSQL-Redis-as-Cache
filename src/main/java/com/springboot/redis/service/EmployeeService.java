package com.springboot.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.redis.model.Employee;
import com.springboot.redis.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	// Cache employee with key as the employee ID
	@Cacheable(value = "employees", key = "#id")
	public Employee getEmployeeById(Long id) {
		logger.info("Fetching employee from database with ID: {}", id);
		return employeeRepository.findById(id).orElse(null);
	}

	// Save employee and evict the cache entry (if exists) for that employee
	@CacheEvict(value = "employees", key = "#employee.empId")
	public Employee saveEmployee(Employee employee) {
		logger.info("Saving employee: {}", employee);
		return employeeRepository.save(employee);
	}

	// Delete employee and evict the cache entry for that employee
	@CacheEvict(value = "employees", key = "#id")
	public void deleteEmployee(Long id) {
		logger.info("Deleting employee with ID: {}", id);
		employeeRepository.deleteById(id);
	}
}