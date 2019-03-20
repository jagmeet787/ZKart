package zkart.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.Account;
import zkart.entity.Order;
import zkart.service.AccountService;
import zkart.service.OrderService;
import zkart.service.UserService;


@RestController
@RequestMapping("/orders")
public class OrderController {

	static final Integer ZKART_USERID = 1;
	static final Integer RETURN_DAYS = 15;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;

	@GetMapping("")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/user/{id}")
	public List<Order> getAllOrdersByUserId(@PathVariable("id") Integer userId) {
		return orderService.getAllOrdersByUserId(userId);
	}

	@GetMapping("/order/{id}")
	public List<Order> getAllOrdersByOrderId(@PathVariable("id") Integer orderId) {
		return orderService.getAllOrdersByOrderId(orderId);
	}

	@GetMapping("/item/{id}")
	public List<Order> getOrdersByItemId(@PathVariable("id") String itemId) {
		return orderService.getAllOrdersByItemId(itemId);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createOrder(Order order) {

		if (!orderService.createOrder(order)) 
			return new ResponseEntity<>("Failed to Create Order.", HttpStatus.BAD_REQUEST);
		//		return new ResponseEntity<>("Success.", HttpStatus.OK);
		//		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);

		// PAYMENT_RECIEVED and ORDER_FAILED
		if (order.getStatus().equals("PAYMENT_RECIEVED")) {
			orderService.updateOrder(order);
			Account userAccount = accountService.getAccountByAccountNumber(order.getBuyerAccountNo());
			Account zkartAccount = accountService.getAccountByUserId(ZKART_USERID);

			System.out.println(userAccount);
			System.out.println(zkartAccount);

			// Update balance
			userAccount.setBalance(userAccount.getBalance() - order.getTotalAmount());
			zkartAccount.setBalance(zkartAccount.getBalance() + order.getTotalAmount());
			// write updated balance to database
			accountService.updateAccount(zkartAccount);
			accountService.updateAccount(userAccount);
		}
		
		return new ResponseEntity<>("Success.", HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateOrder(@PathVariable("id") Integer id, Order order) {
		order.setId(id);
		if(orderService.updateOrder(order))
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update/orderid/{orderId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateOrderbyOrderId(@PathVariable("orderId") Integer orderId, Order order) {
		List<Order> orderDetails = orderService.getAllOrdersByOrderId(orderId);
		if (orderDetails != null) {
			Order o = orderDetails.get(0);
			order.setId(o.getId());
			if (orderService.updateOrder(order))
				return new ResponseEntity<>("Success.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update/orderid")
	public ResponseEntity<String> updateOrderByOrderId(Order order) {
		/**
		 * add recieved date to the database column if (item_shipped) seller has shipped
		 * the item just update the order database
		 *
		 * if (item_recieved) buyer has recieved update the order database + add recive
		 * date
		 *
		 * if (item_cancel) transact buyers money from the flipkart account to the buyer
		 * account and set order status to cancel
		 *
		 * if (item_return) same as cancel but just change the status to return
		 *
		 * if (order_order_completed) send money to seller and update the order table
		 *
		 * 0 PAYMENT_RECIEVED 
		 * 1 ORDER_FAILED 
		 * 2 ORDER_SHIPPED 
		 * 3 ORDER_RECIEVED 
		 * 4 ORDER_CANCELLED 
		 * 5 ORDER_RETURNED x
		 * 6 ORDER_COMPLETED 
		 * 7 ORDER_TERMINATED x
		 * 8 ORDER_COMPLETED_AND_RETURNED x
		 **/
		List<Order> o = orderService.getAllOrdersByOrderId(order.getOrderId());
		if (o == null) 
			return new ResponseEntity<>("Invalid OrderId!", HttpStatus.BAD_REQUEST);
		Order orderDetails = o.get(0);
		String orderStatus = order.getStatus();
		
		System.out.println(orderStatus + "status print " + orderStatus.equals("PAYMENT_RECIEVED"));
		
		if (orderStatus.equals("ORDER_SHIPPED") || orderStatus.equals("ORDER_RETURNED")) {
			orderService.updateOrder(order);
		} else if (orderStatus.equals("ORDER_TERMINATED") || orderStatus.equals("ORDER_CANCELLED") || orderStatus.equals("ORDER_COMPLETED_AND_RETURNED")) {
			
			order.setStatus("ORDER_TERMINATED");
			orderService.updateOrder(order);
			
			Account userAccount = accountService.getAccountByAccountNumber(order.getBuyerAccountNo());
			Account zkartAccount = accountService.getAccountByUserId(ZKART_USERID);
			
			System.out.println(userAccount);
			System.out.println(zkartAccount);
			
			// Update balance
			zkartAccount.setBalance(zkartAccount.getBalance() - orderDetails.getTotalAmount());
			userAccount.setBalance(userAccount.getBalance() + orderDetails.getTotalAmount());

			// write updated balance to database
			accountService.updateAccount(zkartAccount);
			accountService.updateAccount(userAccount);

		} else if (orderStatus.equals("ORDER_COMPLETED") || orderStatus.equals("ORDER_RECIEVED")) {
			
			order.setStatus("ORDER_COMPLETED");
			orderService.updateOrder(order);

			// do a transation from zkart account to the seller account
			String itemId = order.getItemId();
			String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[a-zA-Z]{2,3}";
			Pattern reg = Pattern.compile(pattern);
			Matcher matcher = reg.matcher(itemId);
			String sellerEmail = "";
			if (matcher.find())
				sellerEmail = matcher.group(0);
			Integer sellerUserId = userService.getUserByEmail(sellerEmail).getId();
			System.out.println("sellerEmail " + sellerEmail + " sellerUserId:" + sellerUserId);
			
			Account zkartSellerAccount = accountService.getAccountByUserId(sellerUserId);
			Account zkartAccount = accountService.getAccountByUserId(ZKART_USERID);
			
			System.out.println(zkartSellerAccount);
			System.out.println(zkartAccount);
			
			// Update balance
			zkartAccount.setBalance(zkartAccount.getBalance() - orderDetails.getTotalAmount());
			zkartSellerAccount.setBalance(zkartSellerAccount.getBalance() + orderDetails.getTotalAmount());

			// write updated balance to database
			accountService.updateAccount(zkartAccount);
			accountService.updateAccount(zkartSellerAccount);

		}
		return new ResponseEntity<>("Done.", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete_Order(@PathVariable("id") Integer id) {
		orderService.delete(id);
		return new ResponseEntity<>("Deleted.", HttpStatus.OK);
	}
}

