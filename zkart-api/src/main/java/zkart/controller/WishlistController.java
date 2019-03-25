package zkart.controller;

import java.util.ArrayList;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.Item;
import zkart.entity.Wishlist;
import zkart.service.WishlistService;

@RestController
@RequestMapping(path="/wishlist")
public class WishlistController {
	@Autowired
	private WishlistService wishlistService;
	
	@RequestMapping()
	public ResponseEntity<ArrayList<Wishlist>> getZkartWishlists(){
		return new ResponseEntity<>(wishlistService.getZkartWishlists(),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/create")
	public ResponseEntity<String> addZkartWishlist(@RequestBody Wishlist wishlist){
		if(wishlistService.addZkartWishlist(wishlist)==true) {
			return new ResponseEntity<>("wishlsit added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/item/{id}")
	public ResponseEntity<ArrayList<Wishlist>> getZkartWishlistByItemId(@PathParam("id") Integer id){
		return new ResponseEntity<>(wishlistService.getZkartWishlistByItemId(id),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/user/{id}")
	public ResponseEntity<ArrayList<Wishlist>> getZkartWishlistbyUserId(@PathParam("id") Integer id){
		return new ResponseEntity<>(wishlistService.getZkartWishlistByUserId(id),HttpStatus.OK);
	}
    @RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}")
    public ResponseEntity<String> deleteZkartWishlist(@PathParam("id") Integer id){
    	if(wishlistService.deleteZkartWishlist(id)==true) {
    		return new ResponseEntity<>("success",HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/isInWishlist/{userId}/{itemId}")
    public ResponseEntity<Wishlist> getZkartWishListByUserIdItemId(@PathParam("userId") Integer userId,@PathParam("itemId") Integer itemId){
    	return new ResponseEntity<>(wishlistService.getWishlistByUserIdItemId(userId, itemId),HttpStatus.OK);
    }
  
}
