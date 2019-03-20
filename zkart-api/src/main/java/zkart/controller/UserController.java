package zkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.User;
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
	
	//need to impelmented not working
	@RequestMapping(method = RequestMethod.PUT, value = "/update")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		if (userService.updateUser(user))
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
}