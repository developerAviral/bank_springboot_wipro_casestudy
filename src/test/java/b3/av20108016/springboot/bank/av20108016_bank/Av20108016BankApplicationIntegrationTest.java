package b3.av20108016.springboot.bank.av20108016_bank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Account;
import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;
import b3.av20108016.springboot.bank.av20108016_bank.model.FundTransfer;

//@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Av20108016BankApplicationIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void createCustomer() {

		Customer customer = new Customer(1, "Aviral", "Srivastava", "aviral.srivastava2@wipro.com");

		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity("/customers", request, String.class);

		assertEquals(201, result.getStatusCodeValue());

	}
	
	@Test
	@Order(2)
	public void createSecondCustomer() {

		Customer customer = new Customer(2, "Anmol", "Srivastava", "anmol.srivastava2@wipro.com");

		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity("/customers", request, String.class);

		assertEquals(201, result.getStatusCodeValue());

	}

	@Test
	@Order(3)
	public void createCustomerAccount() {
		Account account = new Account();
		account.setAccountType("Savings");
		account.setBalance(12345f);

		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Account> request = new HttpEntity<>(account, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity("/customers/1/accounts", request, String.class);

		assertEquals(201, result.getStatusCodeValue());

	}
	
	@Test
	@Order(4)
	public void createSecondCustomerAccount() {
		Account account = new Account();
		account.setAccountType("Current");
		account.setBalance(25463f);

		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<Account> request = new HttpEntity<>(account, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity("/customers/2/accounts", request, String.class);

		assertEquals(201, result.getStatusCodeValue());

	}

	@Test
	@Order(5)
	public void getCustomerandAccountByID() {

		Customer customer = this.restTemplate.getForObject("/customers/1/accounts", Customer.class);
		assertEquals("Aviral", customer.getFirstName());
		assertEquals("Srivastava", customer.getLastName());
		assertEquals("aviral.srivastava2@wipro.com", customer.getEmail());

	}

	@Test
	@Order(6)
	public void getCustomerandAccount() {

		Customer[] customer = this.restTemplate.getForObject("/customers/accounts", Customer[].class);

		for (int i = 0; i < customer.length; i++) {
			System.out.println(customer[i].getCustomerId() + "\t" + customer[i].getFirstName());
		}
	}

	@Test
	@Order(7)
	public void amountTransfer() {

		FundTransfer fundTransfer = new FundTransfer(1, 2, 100f);

		HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.set("Content-Type", "application/json");

		HttpEntity<FundTransfer> request = new HttpEntity<>(fundTransfer, headers);

		ResponseEntity<FundTransfer> result = this.restTemplate.exchange("/accounts/transferFund", HttpMethod.PUT,
				request, FundTransfer.class, fundTransfer);

		assertEquals(200, result.getStatusCodeValue());

	}

}
