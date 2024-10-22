package com.springboot.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.redis.model.Employee;
import com.springboot.redis.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		logger.info("Received request to create employee: {}", employee);
		employeeService.saveEmployee(employee);
		logger.info("Employee saved successfully: {}", employee.getEmpId());
		return ResponseEntity.ok("Employee saved successfully!");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long  id) {
		logger.info("Received request to get employee by ID: {}", id);
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null) {
			logger.info("Employee found: {}", employee);
			return ResponseEntity.ok(employee);
		} else {
			logger.warn("Employee with ID {} not found", id);
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long  id) {
		logger.info("Received request to delete employee by ID: {}", id);
		employeeService.deleteEmployee(id);
		logger.info("Employee with ID {} deleted successfully", id);
		return ResponseEntity.ok("Employee deleted successfully!");
	}
}
