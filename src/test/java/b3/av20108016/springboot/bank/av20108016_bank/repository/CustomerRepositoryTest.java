package b3.av20108016.springboot.bank.av20108016_bank.repository;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository repository;

	@Test
	@Order(1)
	void createCustomer() {
		System.out.println("Entering Create Employee");
		Customer customer1=new Customer();
		Customer customer2=new Customer();
		
		customer1.setFirstName("Aviral");
		customer1.setLastName("Srivastava");
		customer1.setEmail("aviral.srivastava2@wipro.com");
		
		customer2.setFirstName("Anmol");
		customer2.setLastName("Srivastava");
		customer2.setEmail("anmol.srivastava2@wipro.com");
		
		repository.save(customer1);
		repository.save(customer2);
		
	}
	
	@Test
	@Order(2)
	void getEmployees() {
		createCustomer();
		Iterable<Customer> customers = repository.findAll();
		
		System.out.println("Customer Details");
		
		
		
		for(Customer customer: customers) {
			System.out.println(customer.getCustomerId()+"\t"+customer.getFirstName());
		}
		
	
		
	}
}
