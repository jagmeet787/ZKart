package zkart.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;


import zkart.entity.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Integer>  {
	public ArrayList<Wishlist> findAllByItemId(Integer itemId);
	public ArrayList<Wishlist> findAllByUserId(Integer userId);
}
