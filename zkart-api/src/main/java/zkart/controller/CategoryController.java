package zkart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public ResponseEntity<String> addZkartCategory(@RequestBody Category category){
		if(categoryService.addZkartCategory(category)==true) {
			return new ResponseEntity<>("category added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<String> updateZkartCategory(@PathVariable("id") Integer id,@RequestBody Category category){
		if(categoryService.updateZkartCategory(category,id)==true) {
			return new ResponseEntity<>("category updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<String> updateZkartCategory(@PathVariable("id") Integer id){
		if(categoryService.deleteZkartCategory(id)==true) {
			return new ResponseEntity<>("category deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}

}
