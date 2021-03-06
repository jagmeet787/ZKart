package zkart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.Category;
import zkart.entity.Item;
import zkart.service.CategoryService;

@CrossOrigin( origins = "*" )
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
		System.out.println("hello");
		if(categoryService.addZkartCategory(category)==true) {
			return new ResponseEntity<>("category added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<String> updateZkartCategory(@PathVariable("id") Integer id,@RequestBody Category category){
		category.setId(id);
		if(categoryService.updateZkartCategory(category)==true) {
			return new ResponseEntity<>("category updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<String> deleteZkartCategory(@PathVariable("id") Integer id){
		if(categoryService.deleteZkartCategory(id)==true) {
			return new ResponseEntity<>("category deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateByName/{existing}/{new}")
	public ResponseEntity<String> updateZkartCategory(@PathVariable("existing") String existing,@PathVariable("new") String newName){
		if(categoryService.updateZkartCategoryByName(existing,newName)==true) {
			return new ResponseEntity<>("category updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteByName/{name}")
	public ResponseEntity<String> deleteZkartCategoryByName(@PathVariable("name") String name){
		if(categoryService.deleteZkartCategoryByName(name)==true) {
			return new ResponseEntity<>("category deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}

}
