package zkart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import zkart.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	public List<User> findByEmail(String email);
	public List<User> findByPhone(String phone);

//	@Modifying(clearAutomatically = true)
//	@Query("update User u set u.firstName = :firstName, "
//			+ "u.lastName = :lastName, "
//			+ "u.imageUrl = :imageUrl, "
//			+ "u.password = :password, "
//			+ "u.address = :address, "
//			+ "u.wallet = :wallet, "
//			+ "u.secret = :secret"
//			+ "where u.email = :email")
//	public int updateUserForEmail(@Param("firstName") String firstName,
//			@Param("lastName") String lastName,
//			@Param("imageUrl") String imageUrl,
//			@Param("password") String password,
//			@Param("address") String address,
//			@Param("wallet") Integer wallet,
//			@Param("secret") String secret,
//			@Param("email") String email);
}
