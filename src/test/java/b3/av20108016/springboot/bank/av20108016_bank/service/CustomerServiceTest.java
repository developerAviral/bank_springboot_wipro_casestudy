package b3.av20108016.springboot.bank.av20108016_bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Account;
import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;
import b3.av20108016.springboot.bank.av20108016_bank.repository.AccountRepository;
import b3.av20108016.springboot.bank.av20108016_bank.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class CustomerServiceTest {

	@InjectMocks
	CustomerService service;
	
	@InjectMocks
	AccountService accountService;
	
	@Mock
	private CustomerRepository repository;
	
	@Mock
	private AccountRepository repo;
	
	@Test

	public void createCustomer() {

		Customer customer = new Customer();
		customer.setCustomerId(10);
		customer.setFirstName("Aviral");
		customer.setLastName("Srivastava");
		customer.setEmail("aviral.srivastava@wipro.com");

		when(repository.save(customer)).thenReturn(customer);
		Customer customer1 = service.createCustomer(customer);
		System.out.println(customer1.toString());
		verify(repository, atLeastOnce()).save(customer);

	}

	@Test
	public void createCustomerAccount() {

		Customer firstCustomer = new Customer();
		firstCustomer.setCustomerId(2);
		firstCustomer.setFirstName("Aviral");
		firstCustomer.setLastName("Srivastava");
		firstCustomer.setEmail("aviral.srivastava@wipro.com");

		Account savingsAccount = new Account();
		savingsAccount.setAccountType("Savings");
		savingsAccount.setBalance(20000f);

		Set<Account> accounts = new HashSet<Account>();
		accounts.add(savingsAccount);

		firstCustomer.setAccounts(accounts);

		Set<Customer> customers = new HashSet<Customer>();
		customers.add(firstCustomer);

		when(repository.save(firstCustomer)).thenReturn(firstCustomer);
	//	when(repository.findById(firstCustomer.getCustomerId())).thenReturn(Optional.of(firstCustomer));

		assertEquals(service.createCustomer((firstCustomer)).getCustomerId(),firstCustomer.getCustomerId());

	}

	
}
