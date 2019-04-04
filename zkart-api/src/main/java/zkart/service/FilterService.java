package zkart.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Filter;
import zkart.entity.SubCategory;
import zkart.repository.FilterRepository;
import zkart.repository.SubCategoryRepository;

@Service
public class FilterService {
	@Autowired
	private FilterRepository filterRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	
	public ArrayList<Filter> getZkartFilters(){
		ArrayList<Filter> filters=new ArrayList<Filter>();
		Iterable<Filter> iterable=filterRepository.findAll();
		Iterator<Filter> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			filters.add(iterator.next());
		}
		return filters;
	}
	public Filter getZkartFilterById(Integer id) {
		return filterRepository.findById(id).get();
	}
	
	public boolean addZkartFilter(Integer catId,String subcatName,String filterName) {
		boolean res=false;
		try {
			System.out.println(catId + " " + subcatName + " " + filterName);
			ArrayList<SubCategory> arr = new ArrayList<SubCategory>();
			arr=subCategoryRepository.findAllByCategoryId(catId);
			System.out.println(arr);
			for (SubCategory i : arr)
			{
				if(i.getSubcategoryName().equals(subcatName))
				{
					Filter filter = new Filter();
					filter.setFilterName(filterName);
					filter.setSubCategory(i);
					filterRepository.save(filter);
					res=true;
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
	
	/*
	 * 
	 * public boolean addZkartFilter(Filter filter) {
		boolean res=true;
		try {
			filterRepository.save(filter);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}

	 * */
	
	public boolean updateZkartFilter(Filter filter) {
		return filterRepository.save(filter)!=null;
}
	
	public boolean deleteZkartFilter(Integer id) {
		boolean res=true;
		try {
			filterRepository.deleteById(id);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	/*
	 	public boolean deleteZkartCategoryByName(String name) {
		boolean res=false;
		try {
			ArrayList<Category> ar=getZkartCategories();
			for(Category i : ar)
			{
				
				if(i.getCategoryName().equals(name))
				{
					deleteZkartCategory(i.getId());
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
	
	public boolean updateZkartCategoryByName(String existing, String newName) {
		boolean res=false;
		try {
			ArrayList<Category> ar=getZkartCategories();
			for(Category i : ar)
			{
				if(i.getCategoryName().equals(existing))
				{
					
					i.setCategoryName(newName);
					updateZkartCategory(i);
					
				
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
	}*/
}
