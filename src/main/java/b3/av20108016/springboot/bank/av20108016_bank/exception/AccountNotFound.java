package b3.av20108016.springboot.bank.av20108016_bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
