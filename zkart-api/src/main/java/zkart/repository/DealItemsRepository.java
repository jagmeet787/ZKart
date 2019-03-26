package zkart.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Dealitems;

public interface DealItemsRepository extends CrudRepository<Dealitems, Integer>{
	public ArrayList<Dealitems> findAllByUserId(Integer userId);
	public ArrayList<Dealitems> findAllByItemId(Integer itemId);
	public ArrayList<Dealitems> findAllByDealId(Integer dealId);
}
