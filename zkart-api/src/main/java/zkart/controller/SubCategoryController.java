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
import zkart.entity.SubCategory;
import zkart.service.SubCategoryService;

@RestController
@RequestMapping(path="/subcategories")
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;
	
	@RequestMapping()
	public ResponseEntity<ArrayList<SubCategory>> getZkartSubCategories(){
		return new ResponseEntity<>(subCategoryService.getZkartSubCategories(),HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<SubCategory> getZkartSubCategoryById(@PathVariable("id") int id){
		return new ResponseEntity<>(subCategoryService.getZkartSubCategoryById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public ResponseEntity<String> addZkartSubCategory(@RequestBody SubCategory subCategory){
		if(subCategoryService.addZkartSubCategory(subCategory)==true) {
			return new ResponseEntity<>("subCategory added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<String> addZkartSubCategory(@PathVariable("id") Integer id,@RequestBody SubCategory subCategory){
		if(subCategoryService.updateZkartSubCategory(subCategory,id)==true) {
			return new ResponseEntity<>("subCategory updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<String> addZkartSubCategory(@PathVariable("id") Integer id){
		if(subCategoryService.deleteZkartSubCategory(id)==true) {
			return new ResponseEntity<>("subCategory deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/category/{id}")
	public ResponseEntity<ArrayList<SubCategory>> getZkartItemsByCategoryId(@PathVariable("id") Integer id){
		return new ResponseEntity<>(subCategoryService.getZkartSubCategoryByCategoryId(id),HttpStatus.OK);
	}
}
