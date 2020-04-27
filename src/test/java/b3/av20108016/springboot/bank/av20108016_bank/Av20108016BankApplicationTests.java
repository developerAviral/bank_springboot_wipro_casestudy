package b3.av20108016.springboot.bank.av20108016_bank;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Account;
import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;
import b3.av20108016.springboot.bank.av20108016_bank.repository.AccountRepository;
import b3.av20108016.springboot.bank.av20108016_bank.repository.CustomerRepository;
import b3.av20108016.springboot.bank.av20108016_bank.service.AccountService;
import b3.av20108016.springboot.bank.av20108016_bank.service.CustomerService;


@SpringBootTest
class Av20108016BankApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AccountService accountService;
	
	@Test
	
	public void test_saveCustomer() {
		System.out.println("Inside the Test Method");
		Customer firstCustomer=new Customer();
		firstCustomer.setFirstName("Aviral");
		firstCustomer.setLastName("Srivastava");
		firstCustomer.setEmail("avr.sr@wipro.com");
		
		Customer secondCustomer=new Customer();
		secondCustomer.setFirstName("Abhishek");
		secondCustomer.setLastName("Nachankar");
		secondCustomer.setEmail("abhishek.kum@wipro.com");
		
		Account savingsAccount=new Account();
		savingsAccount.setAccountType("Savings");
		savingsAccount.setBalance(20000f);
		
		Account currentAccount=new Account();
		currentAccount.setAccountType("Current");
		currentAccount.setBalance(30000f);
		
		Set<Account> accounts=new HashSet<Account>();
		accounts.add(savingsAccount);
		accounts.add(currentAccount);
		
		firstCustomer.setAccounts(accounts);
		secondCustomer.setAccounts(accounts);
		
		
		Set<Customer> customers=new HashSet<Customer>();
		customers.add(firstCustomer);
		customers.add(secondCustomer);
		
		savingsAccount.setCustomers(customers);
		currentAccount.setCustomers(customers);
		
		
		customerRepository.save(firstCustomer);
		customerRepository.save(secondCustomer);		
		
	}
	
	@Transactional
	@Test	
	public void test_getCustomers() {
		
		System.out.println("In get Customers method");
		
		Iterable<Customer> customers = customerRepository.findAll();
		
		Iterator<Customer> itr=customers.iterator();
		
		while(itr.hasNext()) {
			Customer customer=itr.next();
			System.out.println("Customer Details:");
			System.out.println(customer);
			Set<Account> accounts = customer.getAccounts();
			System.out.println("Account Details:");
			for(Account account:accounts) {
				System.out.println(account);
			}
		}
	}
	
	@Transactional
	@Test	
	public void test_getAllAccounts(){
		 Iterable<Account> account = accountService.getAccounts();
		 Iterator<Account> itr=account.iterator();
			
			while(itr.hasNext()) {
				Account accoounts=itr.next();
				System.out.println("Account Details:");
				System.out.println(accoounts);
				Set<Customer> customer = accoounts.getCustomers();
				System.out.println("Customer Details:");
				for(Customer cust:customer) {
					System.out.println(cust);
				}
			}
		}


}
