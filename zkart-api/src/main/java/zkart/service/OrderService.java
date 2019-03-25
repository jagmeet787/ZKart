package zkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Order;
import zkart.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders() {
		ArrayList<Order> list = new ArrayList<Order>();	
		orderRepository.findAll().forEach(list::add);
		return list;
	}

	public List<Order> getAllOrdersByUserId(Integer userId) {
		ArrayList<Order> list = new ArrayList<Order>();	
		orderRepository.findAllByUserId(userId).forEach(list::add);
		return list;
	}

	public List<Order> getAllOrdersByOrderId(Integer orderId) {
		ArrayList<Order> list = new ArrayList<Order>();	
		orderRepository.findAllByOrderId(orderId).forEach(list::add);
		return list;
	}

	public List<Order> getAllOrdersByItemId(String itemId) {
		ArrayList<Order> list = new ArrayList<Order>();	
		orderRepository.findAllByItemId(itemId).forEach(list::add);
		return list;
	}

	public boolean createOrder(Order order) {
		return orderRepository.save(order) != null;
	}

	public boolean updateOrder(Order order) {
		return orderRepository.save(order) != null;
	}

	public void delete(Integer id) {
		orderRepository.deleteById(id);
	}

}
