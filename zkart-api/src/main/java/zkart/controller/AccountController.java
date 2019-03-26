package zkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.Account;
import zkart.entity.User;
import zkart.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping("")
	public List<Account> getAccountss() {
		List<Account> accounts = accountService.getAccounts();
		return accounts;
	}

	@RequestMapping("/accountno/{accountNumber}")
	public Account getAccountByAccountNumber(@PathVariable Integer accountNumber) {
		return accountService.getAccountByAccountNumber(accountNumber);
	}

	@RequestMapping(value = "/user/{userId}", produces = "application/JSON")
	public Account getAccountByUserId(@PathVariable("userId") Integer userId) {
		System.out.println("this function");
		System.out.println("userId: " + userId + ", " + accountService.getAccountByUserId(userId));
		return accountService.getAccountByUserId(userId);
	}

	@RequestMapping("/user/all/{userId}")
	public List<Account> getAllAccountByUser(@PathVariable int userId) {
		System.out.println("userId: " + userId + ", " + accountService.getAllAccountByUserId(userId));
		return accountService.getAllAccountByUserId(userId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/accountno")
	public Account getAccountByAccountNumber(@RequestBody Account account) {
		Account accountDetails = accountService.getAccountByAccountNumber(account.getAccountNumber());
		if (accountDetails == null)
			return null;
		if ( !(accountDetails.getPin().equals( account.getPin() )) )
			return account;
		else
			return accountDetails;
	}
	
	// why to use this 
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}/")
	public ResponseEntity<String> updateAccount(@PathVariable("id") Integer id,
			@RequestBody Account account) {
		if(account.getPin() == null) 
			return new ResponseEntity<>("Enter PIN.", HttpStatus.BAD_REQUEST);
		account.setUser(accountService.getAccountById(id).getUser());
		account.setId(id);
		Account accountDetails = accountService.getAccountById(id);
		if (accountDetails.getPin() == account.getPin()) {
			if (accountService.updateAccount(account)) 
				return new ResponseEntity<>("Success.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/addmoney")
	public ResponseEntity<String> addMoneyAccount(@RequestBody Account account) {
		
		if (account == null)
			return new ResponseEntity<>("Send account details.", HttpStatus.BAD_REQUEST);
		
		if(account.getAccountNumber() == null)
			return new ResponseEntity<>("No Account number specified.", HttpStatus.BAD_REQUEST);
		
		if(account.getPin() == null)
			return new ResponseEntity<>("Enter PIN.", HttpStatus.BAD_REQUEST);
		
		if(account.getBalance() == null)
			return new ResponseEntity<>("Enter amount.", HttpStatus.BAD_REQUEST);
		
		Account accountDetails = accountService.getAccountByAccountNumber(account.getAccountNumber());
		if(!account.getPin().equals(accountDetails.getPin()))
			return new ResponseEntity<>("Incorrect PIN.", HttpStatus.BAD_REQUEST);

		accountDetails.setBalance(account.getBalance());
		
		System.out.println("Updated account details: " + accountDetails);
		
		if (accountService.updateAccount(accountDetails))
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create/{userId}")
	public ResponseEntity<String> addAccount(@RequestBody Account account, @PathVariable Integer userId) {
		User user = new User(userId);
		account.setUser(user);
		System.out.println("addAccount(): " + account);
		if (accountService.updateAccount(account))
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
	}

}