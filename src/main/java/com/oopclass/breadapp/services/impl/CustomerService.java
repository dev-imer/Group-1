package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Customer;
import com.oopclass.breadapp.repository.CustomerRepository;
import com.oopclass.breadapp.services.ICustomerService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class CustomerService implements ICustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer entity) {
		return customerRepository.save(entity);
	}

	@Override
	public Customer update(Customer entity) {
		return customerRepository.save(entity);
	}

	@Override
	public void delete(Customer entity) {
		customerRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer find(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Customer> customers) {
		customerRepository.deleteInBatch(customers);
	}
	
}
