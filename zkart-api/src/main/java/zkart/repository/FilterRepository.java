package zkart.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.Filter;

public interface FilterRepository extends CrudRepository<Filter, Integer>{
	
	public ArrayList<Filter> findAllBySubCategoryId(Integer subCategoryId);

}
