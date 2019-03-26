package zkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Orders;
import zkart.repository.OrdersRepository;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	public List<Orders> getAllOrders() {
		ArrayList<Orders> list = new ArrayList<Orders>();	
		ordersRepository.findAll().forEach(list::add);
		return list;
	}

	public List<Orders> getAllOrdersByUserId(Integer userId) {
		ArrayList<Orders> list = new ArrayList<Orders>();	
		ordersRepository.findAllByUserId(userId).forEach(list::add);
		return list;
	}

	public List<Orders> getAllOrdersByOrdersId(Integer orderId) {
		ArrayList<Orders> list = new ArrayList<Orders>();	
		ordersRepository.findAllByOrderId(orderId).forEach(list::add);
		return list;
	}

	public List<Orders> getAllOrdersByItemId(String itemId) {
		ArrayList<Orders> list = new ArrayList<Orders>();	
		ordersRepository.findAllByItemId(itemId).forEach(list::add);
		return list;
	}

	public boolean createOrder(Orders order) {
		return ordersRepository.save(order) != null;
	}

	public boolean updateOrder(Orders order) {
		return ordersRepository.save(order) != null;
	}

	public void delete(Integer id) {
		ordersRepository.deleteById(id);
	}

	public Orders getOrderById(Integer id) {
		return ordersRepository.findById(id).get();
	}

}
