package zkart.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Account;
import zkart.entity.Wishlist;
import zkart.repository.WishlistRepository;

@Service
public class WishlistService {
	@Autowired
	private WishlistRepository wishlistRepository;
	
	public ArrayList<Wishlist> getZkartWishlists(){
		ArrayList<Wishlist> wishlist=new ArrayList<Wishlist>();
		wishlistRepository.findAll().forEach(wishlist::add);
		return wishlist;
	}
	
	public boolean addZkartWishlist(Wishlist wishlist) {
		boolean res=true;
		try {
			wishlistRepository.save(wishlist);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	
	public ArrayList<Wishlist> getZkartWishlistByItemId(Integer itemId){
		return wishlistRepository.findAllByItemId(itemId);
	}
	public ArrayList<Wishlist> getZkartWishlistByUserId(Integer userId){
		return wishlistRepository.findAllByUserId(userId);
	}
	public boolean deleteZkartWishlist(Integer wishlistId) {
		boolean res=true;
		try {
			wishlistRepository.deleteById(wishlistId);
		}catch(Exception e) {
			res=false;
			System.out.print(e);
		}
		return res;
	}
	
	public Wishlist getWishlistByUserIdItemId(Integer userId,Integer itemId) {
		ArrayList<Wishlist> wishlists=wishlistRepository.findAllByUserId(userId);
		Wishlist result=null;
		for(Wishlist wishlist:wishlists) {
			if(wishlist.getItem().getId().equals(itemId)) {
				result=wishlist;
			}
		}
		System.out.println(result);
		return result;
	}
	/*ArrayList<Account> list = new ArrayList<Account>();
	accountRepository.findAll().forEach(list::add);*/
}
