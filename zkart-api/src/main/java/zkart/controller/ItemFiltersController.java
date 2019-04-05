package zkart.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.ItemFilters;
import zkart.service.ItemFiltersService;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping(path="/itemFilter")
public class ItemFiltersController {
	
	
	
	@Autowired
	private ItemFiltersService itemFiltersService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public ResponseEntity<String> addZkartCategory(@RequestBody ItemFilters itemFilter){
		System.out.println("item Filter:"+itemFilter.getFilterValues());
		if(itemFiltersService.addZkartItemFilter(itemFilter)==true) {
			return new ResponseEntity<>("filter added added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping()
	public ResponseEntity<ArrayList<ItemFilters>> getAllZkartItemFilters(){
		return new ResponseEntity<>(itemFiltersService.getAllZkartItemFilters(),HttpStatus.OK);
	}
}
