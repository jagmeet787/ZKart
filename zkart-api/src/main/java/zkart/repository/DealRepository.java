package zkart.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import zkart.entity.Deal;

public interface DealRepository extends CrudRepository<Deal, Integer>{
	
	//public ArrayList<Deal> findAllByDealitemsId(Integer dealItemsId);
	//public ArrayList<Deal> findAllByUserId(Integer userId);
	
	/*@Query("select d from deal d,dealitems e where d.id=e.dealid and e.itemid=?1")
	public List<Deal> findDealsByItemId(String itemId);*/
}
