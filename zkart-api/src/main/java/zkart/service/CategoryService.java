package zkart.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Category;
import zkart.entity.Item;
import zkart.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ArrayList<Category> getZkartCategories(){
		ArrayList<Category> categories=new ArrayList<Category>();
		Iterable<Category> iterable=categoryRepository.findAll();
		Iterator<Category> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			categories.add(iterator.next());
		}
		return categories;
	}
	public Category getZkartCategoryById(Integer id) {
		return categoryRepository.findById(id).get();
	}
	
	public boolean addZkartCategory(Category category) {
		boolean res=true;
		try {
			categoryRepository.save(category);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	
	public boolean updateZkartCategory(Category category,Integer id) {
		category.setId(id);
		boolean res=true;
		try {
			categoryRepository.save(category);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	
	public boolean deleteZkartCategory(Integer id) {
		boolean res=true;
		try {
			categoryRepository.deleteById(id);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
}
