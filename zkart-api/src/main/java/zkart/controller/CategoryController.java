package zkart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.Category;
import zkart.entity.Item;
import zkart.service.CategoryService;

@RestController
@RequestMapping(path="/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping()
	public ResponseEntity<ArrayList<Category>> getZkartCategories(){
		return new ResponseEntity<>(categoryService.getZkartCategories(),HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Category> getZkartCategories(@PathVariable("id") int id){
		return new ResponseEntity<>(categoryService.getZkartCategoryById(id),HttpStatus.OK);
	}

}
