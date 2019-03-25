package zkart.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import zkart.entity.SubCategory;

public interface SubCategoryRepository extends CrudRepository<SubCategory,Integer> {
	public ArrayList<SubCategory> findAllByCategoryId(Integer categoryId);
}
