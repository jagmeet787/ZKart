package zkart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
	List<Order> findAllByUserId(Integer userId);
	List<Order> findAllByOrderId(Integer orderId);
	List<Order> findAllByItemId(String itemId);
}
