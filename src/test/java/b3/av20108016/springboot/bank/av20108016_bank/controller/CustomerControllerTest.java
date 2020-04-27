package b3.av20108016.springboot.bank.av20108016_bank.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Customer;
import b3.av20108016.springboot.bank.av20108016_bank.repository.CustomerRepository;
import b3.av20108016.springboot.bank.av20108016_bank.service.CustomerService;

@ExtendWith(SpringExtension.class) 
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;
	
	@Test
	public void saveCustomer() throws Exception {

		Customer mockCustomer = new Customer(1, "Aviral", "Srivastava", "aviral.srivastava@wipro.com");

		Mockito.when(service.createCustomer(Mockito.any(Customer.class))).thenReturn(mockCustomer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers").accept(MediaType.APPLICATION_JSON)
				.content(asJsonString(mockCustomer)).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(201, response.getStatus());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
