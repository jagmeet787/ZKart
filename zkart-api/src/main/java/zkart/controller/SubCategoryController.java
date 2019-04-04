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
import zkart.entity.SubCategory;
import zkart.service.SubCategoryService;

@CrossOrigin( origins = "*" )
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
		subCategory.setId(id);
		if(subCategoryService.updateZkartSubCategory(subCategory)==true) {
			return new ResponseEntity<>("subCategory updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<String> deleteZkartSubCategory(@PathVariable("id") Integer id){
		if(subCategoryService.deleteZkartSubCategory(id)==true) {
			return new ResponseEntity<>("subCategory deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/category/{id}")
	public ResponseEntity<ArrayList<SubCategory>> getZkartSubcategoriesByCategoryId(@PathVariable("id") Integer id){
		return new ResponseEntity<>(subCategoryService.getZkartSubCategoryByCategoryId(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateByName/{catId}/{existing}/{new}")
	public ResponseEntity<String> updateZkartCategory(@PathVariable("catId")Integer catId,@PathVariable("existing") String existing,@PathVariable("new") String newName){
		if(subCategoryService.updateZkartSubCategoryByName(catId,existing,newName)==true) {
			return new ResponseEntity<>("category updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteByName/{catId}/{name}")
	public ResponseEntity<String> deleteZkartCategoryByName(@PathVariable("catId")Integer catId,@PathVariable("name") String name){
		if(subCategoryService.deleteZkartSubCategoryByName(catId,name)==true) {
			return new ResponseEntity<>("category deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}

}
