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

import zkart.entity.Filter;
import zkart.service.FilterService;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping(path="/filter")
public class FilterController {
	
	@Autowired
	private FilterService filterService;

	@RequestMapping("")
	public ResponseEntity<ArrayList<Filter>> getZkartFilters(){
		return new ResponseEntity<>(filterService.getZkartFilters(),HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Filter> getZkartFilters(@PathVariable("id") int id){
		return new ResponseEntity<>(filterService.getZkartFilterById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/create/{catId}/{subcatName}/{filterName}")
	public ResponseEntity<String> addZkartFilter(@PathVariable("catId")int catId,@PathVariable("subcatName")String subcatName,@PathVariable("filterName")String filterName){
		System.out.println("hello from createFilter");
		if(filterService.addZkartFilter(catId,subcatName,filterName)==true) {
			return new ResponseEntity<>("filter added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<String> updateZkartFilter(@PathVariable("id") Integer id,@RequestBody Filter filter){
		filter.setId(id);
		if(filterService.updateZkartFilter(filter)==true) {
			return new ResponseEntity<>("filter updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<String> deleteZkartFilter(@PathVariable("id") Integer id){
		if(filterService.deleteZkartFilter(id)==true) {
			return new ResponseEntity<>("filter deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(method=RequestMethod.GET,value="/subcategory/{id}")
	public ResponseEntity<ArrayList<Filter>> getAllZkartFiltesBySubCategoryId(@PathVariable("id") Integer subCategoryId){
		return new ResponseEntity<>(filterService.getAllZkartFiltersBySubCategoryId(subCategoryId),HttpStatus.OK);
	}
/*
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
*/
}
