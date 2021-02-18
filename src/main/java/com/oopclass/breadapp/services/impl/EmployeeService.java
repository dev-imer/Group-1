package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Employee;
import com.oopclass.breadapp.repository.EmployeeRepository;
import com.oopclass.breadapp.services.IEmployeeService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee save(Employee entity) {
		return employeeRepository.save(entity);
	}

	@Override
	public Employee update(Employee entity) {
		return employeeRepository.save(entity);
	}

	@Override
	public void delete(Employee entity) {
		employeeRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee find(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Employee> employees) {
		employeeRepository.deleteInBatch(employees);
	}
	
}
