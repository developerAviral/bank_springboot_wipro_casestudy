package b3.av20108016.springboot.bank.av20108016_bank.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b3.av20108016.springboot.bank.av20108016_bank.entity.Account;
import b3.av20108016.springboot.bank.av20108016_bank.exception.AccountNotFound;
import b3.av20108016.springboot.bank.av20108016_bank.exception.InsufficientFundException;
import b3.av20108016.springboot.bank.av20108016_bank.model.FundTransfer;
import b3.av20108016.springboot.bank.av20108016_bank.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts =(List<Account>) repository.findAll();
		return accounts;
	}
	
	public Account getAccount(int id) {
		Optional<Account> accounts = repository.findById(id);
		Account account = accounts.get();
		return account;
	}
	
	public Account createAccount(Account acc) {
		return repository.save(acc);
	}
	
	public Account updateAccount(int id, Account acc) {
		Optional<Account> accounts = repository.findById(id);
		Account account = accounts.orElse(null);
		if(accounts.isPresent()) {
		account.setAccountType(acc.getAccountType());
		account.setBalance(acc.getBalance());		
		}
		return repository.save(account);
	}
	
	@Transactional
	public Account transferFund(FundTransfer transferFund) {
		
		Account fromAccount = this.getAccount(transferFund.getFromAccountNumber());
		Account toAccount = this.getAccount(transferFund.getToAccountNumber());
		
		if(fromAccount == null || toAccount == null)
			throw new AccountNotFound("Invalid Account Number!!");
		
		if(fromAccount.getBalance() - transferFund.getAmount() <=0)
			throw new InsufficientFundException("Insufficient Funds");
		double fromAccountAmount = fromAccount.getBalance() - transferFund.getAmount();
		double toAccountAmount = fromAccount.getBalance() + transferFund.getAmount();		
		fromAccount.setBalance(fromAccountAmount);
		toAccount.setBalance(toAccountAmount);
		repository.save(fromAccount);
		return repository.save(toAccount);
		 
	}
	
	public Account deleteAccount(int id) {
		Optional<Account> accounts = repository.findById(id);
		Account account = accounts.get();
		repository.delete(account);
		
		return account;
	}

}
