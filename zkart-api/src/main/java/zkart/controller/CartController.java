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

import zkart.entity.Cart;
import zkart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping("")
	public List<Cart> getAllCartItems() {
		return cartService.getAllCartItems();
	}

	@GetMapping("/{id}")
	public Cart getCartItemById(@PathVariable("id") Integer id) {
		return cartService.getCartItemById(id);
	}

	@GetMapping("/user/{userId}")
	public List<Cart> getCartItemsByUserId(@PathVariable("userId") Integer userId) {
		return cartService.getCartItemByUserId(userId);
	}

	@RequestMapping(value = "/user/listingid/{itemId}/{userId}", method = RequestMethod.POST)
	public Cart getCartItemsByItemId(@PathVariable("itemId") String itemId, @PathVariable("userId") Integer userId) {
		return cartService.getCartItemByItemId(itemId, userId);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> addCartItem(@RequestBody Cart cart) {
		if (cartService.addToCart(cart)) 
			return new ResponseEntity<>("Added to cart.", HttpStatus.OK);
		return new ResponseEntity<>("Unable to add to cart.", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCartItem(@PathVariable("id") Integer id, Cart cart) {
		cart.setId(id);
		if(cartService.updateOrder(cart))
			return new ResponseEntity<>("Success.", HttpStatus.OK);
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCartItem(@PathVariable("id") Integer id) {
		cartService.delete(id);
		return new ResponseEntity<>("Deleted.", HttpStatus.OK);
	}
}
