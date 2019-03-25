package zkart.repository;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.repository.CrudRepository;

import zkart.entity.Item;


public interface ItemRepository extends CrudRepository<Item, Integer> {
	
	public Item findAllByItemId(String itemId);
	public ArrayList<Item> findAllBySubCategoryId(Integer subcategoryId);
	public ArrayList<Item> findAllByUserId(Integer sellerId);
	
}
