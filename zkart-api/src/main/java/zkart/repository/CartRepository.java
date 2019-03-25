package zkart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>{
	List<Cart> findAllByUserId(Integer userId);
}
