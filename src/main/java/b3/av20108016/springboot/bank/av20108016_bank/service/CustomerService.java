package b3.av20108016.springboot.bank.av20108016_bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;
import b3.av20108016.springboot.bank.av20108016_bank.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;
	
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		List<Customer> customers =(List<Customer>) repository.findAll();
		return customers;
	}
	
	public Customer getCustomer(int id) {
		Optional<Customer> customers = repository.findById(id);
		Customer customer = customers.orElse(null);
		return customer;
	}
	
	public Customer createCustomer(Customer customer) {
		return repository.save(customer);
	}
	
	public Customer updateCustomer(int id, Customer cust) {
		Optional<Customer> customers = repository.findById(id);
		Customer customer = customers.get();
		customer.setFirstName(cust.getFirstName());
		customer.setLastName(cust.getLastName());
		customer.setEmail(cust.getEmail());
		
		return repository.save(customer);
	}
	
	public Customer deleteCustomer(int id) {
		Optional<Customer> customers = repository.findById(id);
		Customer customer = customers.get();
		repository.delete(customer);
		
		return customer;
	}

}
