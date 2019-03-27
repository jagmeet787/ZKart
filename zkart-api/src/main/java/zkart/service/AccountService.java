package zkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Account;
import zkart.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	public List<Account> getAccounts() {
		ArrayList<Account> list = new ArrayList<Account>();
		accountRepository.findAll().forEach(list::add);
		return list;
	}
	public Account getAccountByAccountNumber(Integer accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}
	public Account getAccountByUserId(Integer userId) {
		return accountRepository.findByUserId(userId).get(0);
	}
	public List<Account> getAllAccountByUserId(Integer userId) {
		ArrayList<Account> list = new ArrayList<Account>();
		accountRepository.findByUserId(userId).forEach(list::add);
		return list;
	}
	public boolean updateAccount(Account account) {
		return accountRepository.save(account) != null;
	}
	public Account getAccountById(Integer id) {
		return accountRepository.findById(id).get();
	}
	public void deleteAccountById(Integer id) {
		accountRepository.deleteById(id);
	}

}

