package zkart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Integer>{
	List<Orders> findAllByUserId(Integer userId);
	List<Orders> findAllByOrderId(Integer orderId);
	List<Orders> findAllByItemId(String itemId);
}
