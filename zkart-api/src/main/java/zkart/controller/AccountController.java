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
	// not working seriliazable error
	@RequestMapping("")
	public List<Account> getAccountss() {
		List<Account> accounts = accountService.getAccounts();
		return accounts;
	}
	// seriliazable error
	@RequestMapping("/accountno/{accountNumber}")
	public Account getAccountByAccountNumber(@PathVariable Integer accountNumber) {
		return accountService.getAccountByAccountNumber(accountNumber);
	}
	// not working
	@RequestMapping("/user/{userId}")
	public Account getAccountByUserId(@PathVariable("userId") Integer userId) {
		System.out.println("userId: " + userId + ", " + accountService.getAccountByUserId(userId));
		return accountService.getAccountByUserId(userId);
	}
	//same error data is fetched fine in all these
	@RequestMapping("/user/all/{userId}")
	public List<Account> getAllAccountByUser(@PathVariable int userId) {
		System.out.println("userId: " + userId + ", " + accountService.getAllAccountByUserId(userId));
		return accountService.getAllAccountByUserId(userId);
	}

	//
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
	
	// updates everthing provided buggy
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}/{userId}")
	public ResponseEntity<String> updateAccount(@PathVariable("id") Integer id,
			@RequestBody Account account,
			@PathVariable("userId") Integer userId) {
		
		User user = new User(userId);
		account.setUser(user);
		account.setId(id);
		if (accountService.updateAccount(account)) 
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
		
	}
	
	//d buggy method creates new entry for update method in db
	@RequestMapping(method = RequestMethod.PUT, value = "/addmoney/{userId}")
	public ResponseEntity<String> addMoneyAccount(@RequestBody Account account, @PathVariable Integer userId) {
		User user = new User(userId);
		account.setUser(user);
		System.out.println("addMoneyAccount(): " + account);
		if (accountService.updateAccount(account))
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