package zkart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.ItemDetails;

public interface ItemDetailsRepository extends CrudRepository<ItemDetails,Integer>{

	public List<ItemDetails> findAllByItemId(Integer itemId);

	public void deleteAllByItemId(Integer itemId);
	
}
