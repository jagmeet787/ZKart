package zkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.User;
import zkart.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		ArrayList<User> list = new ArrayList<User>();
		userRepository.findAll().forEach(list::add);
		return list;
	}

	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).get(0);
	}

	public User getUserByPhone(String phone) {
		return userRepository.findByPhone(phone).get(0);
	}

	public boolean saveUser(User user) {
		return userRepository.save(user) != null;
	}
	
	public boolean updateUser(User user) {
		return false;
//		return userRepository.updateUserForEmail(user.getFirstName(), 
//				user.getLastName(), user.getImageUrl(), user.getPassword(), 
//				user.getAddress(), user.getWallet(), user.getSecret(), user.getEmail()) != 0;
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}
