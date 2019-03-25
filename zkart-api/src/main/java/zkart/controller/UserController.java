package zkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.User;
import zkart.service.EMailService;
import zkart.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") int id) {
		return userService.getUserById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/email")
	public User getUserByEmail(@RequestBody User user) {
		User userDetails = userService.getUserByEmail(user.getEmail());
		
		System.out.println("getUserbyEmail(): user: " + user);
		System.out.println("getUserbyEmail(): user_details: " + userDetails);
		
		return authenticateUser(user, userDetails);
	}
	
	@PostMapping("/resetPassword")
	public User resetPassword(@RequestBody User user) {
		User userDetails = userService.getUserByEmail(user.getEmail());
		if (userDetails == null)
			return null;
		String secret = generateRandomPassword();
		userDetails.setPassword(secret);
		userDetails.setSecret(secret);
		userService.updateUser(userDetails);
		EMailService.sendEmail(userDetails.getEmail(), 
				"Your new Password for ZKart.", 
				"Hello " + userDetails.getFirstName() + " " + userDetails.getLastName()
				+ ",<br>" + "We have recieved your request for resetting password.<br>"
				+ "Your new password is <b>" + userDetails.getSecret() + "</b>.<br>"
				+ "Use this password to login in ZKart.<br>");
		return userDetails;
	}
	
	public String generateRandomPassword() {
		String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(8);
		for(int i = 0; i < 8; i++) {
			int index = (int) (alphabets.length() * Math.random());
			sb.append(alphabets.charAt(index));
		}
		return sb.toString();
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/gmail")
	public User getUserByGmail(@RequestBody User user) {
		return userService.getUserByEmail(user.getEmail());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/phone")
	public User getUserbyPhone(@RequestBody User user) {
		System.out.println(user);
		User userDetails = userService.getUserByPhone(user.getPhone());
		return authenticateUser(user, userDetails);
	}
	
	private User authenticateUser(User user, User userDetails) {
		if (userDetails == null)
			return null;
		if ( !( user.getPassword().equals(userDetails.getPassword()) ) )
			return user;
		else
			return userDetails;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		System.out.println(user.getFirstName());
		System.out.println(user);
		if (userService.saveUser(user))
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
	}
	
	//need to send id along with the object
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		user.setId(id);
		if (userService.updateUser(user))
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
}