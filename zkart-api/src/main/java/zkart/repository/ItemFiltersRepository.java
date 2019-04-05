package zkart.repository;


import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.ItemFilters;


public interface ItemFiltersRepository  extends CrudRepository<ItemFilters,Integer>{
	public ArrayList<ItemFilters> findByItemId(Integer itemId);
}
