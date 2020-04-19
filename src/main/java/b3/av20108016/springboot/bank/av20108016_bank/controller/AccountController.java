package b3.av20108016.springboot.bank.av20108016_bank.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import b3.av20108016.springboot.bank.av20108016_bank.entity.Account;
import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;
import b3.av20108016.springboot.bank.av20108016_bank.exception.AccountNotFound;
import b3.av20108016.springboot.bank.av20108016_bank.exception.CustomerNotFound;
import b3.av20108016.springboot.bank.av20108016_bank.model.FundTransfer;
import b3.av20108016.springboot.bank.av20108016_bank.service.AccountService;
import b3.av20108016.springboot.bank.av20108016_bank.service.CustomerService;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		
		return accountService.getAccounts();
		
	}
	@GetMapping("/accounts/{id}")
	public Account getAccount(@PathVariable int id) {
		Account tempAccount=accountService.getAccount(id);
		if(tempAccount==null) {
			throw new AccountNotFound("Not Found-->"+id);
			
		}
		
		return tempAccount;
		
		
	}
	
	@GetMapping("/customers/accounts")
	public List<Customer> getCustomersAccounts() {		
		return customerService.getCustomers();		
	}
	
	@GetMapping("/customers/{customerId}/accounts")
	public Customer getCustomerAccount(@PathVariable int customerId) {
		Customer tempCustomer=customerService.getCustomer(customerId);
		if(tempCustomer==null) {
			throw new CustomerNotFound("Customer Not Found-->"+customerId);			
		}
		
		return tempCustomer;
		
		
	}
	
	@PostMapping("/accounts")
	public ResponseEntity<Void> createAccount(@RequestBody Account account) {
		Account savedAccount=accountService.createAccount(account);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAccount.getAccountNumber()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PostMapping("/customers/{customerId}/accounts")
	public ResponseEntity<Void> createCustomerAccount(@PathVariable int customerId,
														@RequestBody Account account){
		
		Customer customer = customerService.getCustomer(customerId);
		
		if(customer == null)
			throw new CustomerNotFound("Customer doesn't exists");
		
		Set<Account> accountSet = new HashSet<>();
		accountSet.add(account);
		
		customer.setAccounts(accountSet);
		//Set<Customer> customers=new HashSet<Customer>();
		//customers.add(customer);
		
		//account.setCustomers(customers);
		
		/*
		 * Set<Customer> customerSet = new HashSet<>(); customerSet.add(customer);
		 */	

		//account.setCustomers(customerSet);
		
		Customer tempCustomer = customerService.createCustomer(customer);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tempCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping("/accounts/{id}")
	public Account updateAccount(@PathVariable int id, @RequestBody Account acc) {
		Account tempAccount=accountService.updateAccount(id,acc);
		if(tempAccount==null) {
			throw new AccountNotFound("Account not Found-->"+id);
			
		}
		return tempAccount;
		
		
	}
	
	@PutMapping("/accounts/transferFund")
	public Account transferFund(@RequestBody FundTransfer fundTransfer) {
		Account tempAccount=accountService.transferFund(fundTransfer);
		return tempAccount;
		
		
	}
	
	@DeleteMapping("/accounts/{id}")
	public void deleteEmployee(@PathVariable int id) {
		Account tempAccount=accountService.deleteAccount(id);
		if(tempAccount==null) {
			throw new AccountNotFound("Not Found-->"+id);
			
		}
		
	}

}
