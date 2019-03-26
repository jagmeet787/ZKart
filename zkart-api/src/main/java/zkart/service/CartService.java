package zkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Cart;
import zkart.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	public List<Cart> getAllCartItems() {
		ArrayList<Cart> list = new ArrayList<Cart>();
		cartRepository.findAll().forEach(list::add);
		return list;
	}

	public Cart getCartItemById(Integer id) {
		return cartRepository.findById(id).get();
	}

	public List<Cart> getCartItemByUserId(Integer userId) {
		ArrayList<Cart> list = new ArrayList<Cart>();
		cartRepository.findAllByUserId(userId).forEach(list::add);
		return list;
	}

	public Cart getCartItemByItemId(String itemId, Integer userId) {
		ArrayList<Cart> list = new ArrayList<Cart>();
		cartRepository.findAllByUserId(userId).stream().filter(i -> i.getItemId().equals(itemId)).forEach(list::add);;
		return list == null ? null : list.get(0);
	}

	public boolean addToCart(Cart cart) {
		return cartRepository.save(cart) != null;
	}

	public void delete(Integer id) {
		cartRepository.deleteById(id);
	}

	public boolean updateOrder(Cart cart) {
		return cartRepository.save(cart) != null;
	}
	
}
