package b3.av20108016.springboot.bank.av20108016_bank.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;
import b3.av20108016.springboot.bank.av20108016_bank.exception.CustomerNotFound;
import b3.av20108016.springboot.bank.av20108016_bank.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		return customerService.getCustomers();
		
	}
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer tempCustomer=customerService.getCustomer(id);
		if(tempCustomer==null) {
			throw new CustomerNotFound("Not Found-->"+id);
			
		}
		
		return tempCustomer;		
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer=customerService.createCustomer(customer);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		Customer tempCustomer=customerService.updateCustomer(id,customer);
		if(tempCustomer==null) {
			throw new CustomerNotFound("Not Found-->"+id);
			
		}
		return tempCustomer;
		
		
	}
	
	@DeleteMapping("/customers/{id}")
	public void deleteEmployee(@PathVariable int id) {
		Customer tempCustomer=customerService.deleteCustomer(id);
		if(tempCustomer==null) {
			throw new CustomerNotFound("Not Found-->"+id);
			
		}
		
	}
}
