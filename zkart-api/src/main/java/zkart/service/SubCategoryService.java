package zkart.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zkart.entity.Category;
import zkart.entity.SubCategory;
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
	public boolean updateZkartSubCategory(SubCategory subCategory) {
		
		return subCategoryRepository.save(subCategory)!=null;

		/*boolean res=true;
		try {
			subCategoryRepository.save(subCategory);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;*/
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
		System.out.println("getZkartSubCategoryByCategoryId()");
		return subCategoryRepository.findAllByCategoryId(categoryId);
	}
	
 	public boolean deleteZkartSubCategoryByName(Integer catId,String name) {
	boolean res=false;
	try {
		ArrayList<SubCategory> ar=getZkartSubCategoryByCategoryId(catId);
		for(SubCategory i : ar)
		{
			
			if(i.getSubcategoryName().equals(name))
			{
				deleteZkartSubCategory(i.getId());
				res=true;
				System.out.println("deleted");
				break;
			}
		}
		return res;
		
	}catch(Exception e) {
		res=false;
		System.out.println(e);
	}
	return res;
}

public boolean updateZkartSubCategoryByName(Integer catId,String existing, String newName) {
	boolean res=false;
	try {
		ArrayList<SubCategory> ar=getZkartSubCategoryByCategoryId(catId);
		for(SubCategory i : ar)
		{
			if(i.getSubcategoryName().equals(existing))
			{
				i.setSubcategoryName(newName);
				updateZkartSubCategory(i);
				/*
				deleteZkartSubCategory(i.getId());
				i.setSubcategoryName(newName);
				subCategoryRepository.save(i);*/
				res=true;
				System.out.println("updated");
				break;
			}
		}
		return res;
	}catch(Exception e) {
		res=false;
		System.out.println(e);
	}
	return res;
}
}
