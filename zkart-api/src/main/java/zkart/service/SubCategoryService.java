package zkart.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zkart.entity.Category;
import zkart.entity.SubCategory;
import zkart.repository.SubCategoryRepository;
import zkart.repository.SubCategoryRepository;

@Service
public class SubCategoryService {
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	public ArrayList<SubCategory> getZkartSubCategories(){
		ArrayList<SubCategory> subCategories=new ArrayList<SubCategory>();
		Iterable<SubCategory> iterable=subCategoryRepository.findAll();
		Iterator<SubCategory> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			subCategories.add(iterator.next());
		}
		return subCategories;
	}
	public SubCategory getZkartSubCategoryById(Integer id) {
		return subCategoryRepository.findById(id).get();
	}
	
	public boolean addZkartSubCategory(SubCategory subCategory) {
		boolean res=true;
		try {
			subCategoryRepository.save(subCategory);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	public boolean updateZkartSubCategory(SubCategory subCategory,Integer id) {
		boolean res=true;
		subCategory.setId(id);
		try {
			subCategoryRepository.save(subCategory);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	
	public boolean deleteZkartSubCategory(Integer id) {
		boolean res=true;
		try {
			subCategoryRepository.deleteById(id);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	public ArrayList<SubCategory> getZkartSubCategoryByCategoryId(Integer categoryId) {
		return subCategoryRepository.findAllByCategoryId(categoryId);
	}
}
