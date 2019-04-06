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
import zkart.entity.FilterValues;
import zkart.service.FilterService;
import zkart.service.FilterValuesService;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping(path="/filterValues")
public class FilterValuesController {
	
	@Autowired
	private FilterValuesService filterValuesService;

	@RequestMapping("")
	public ResponseEntity<ArrayList<FilterValues>> getZkartFilterValues(){
		return new ResponseEntity<>(filterValuesService.getZkartFilterValues(),HttpStatus.OK);
	}

	@RequestMapping("/{filterId}")
	public ResponseEntity<ArrayList<FilterValues>> getZkartFilterValuesByFilterId(@PathVariable("filterId")Integer filterId){
		return new ResponseEntity<>(filterValuesService.getZkartFilterValuesByFilterId(filterId),HttpStatus.OK);
	}
	
	
@RequestMapping(method = RequestMethod.POST, value = "/create/{filterId}/{value}")
	public ResponseEntity<String> addZkartFilterValues(@PathVariable("filterId")int filterId,@PathVariable("value")String value){
		System.out.println("createFilterValues " + filterId + " " + value);
		if(filterValuesService.addZkartFilterValues(filterId,value)==true) {
			return new ResponseEntity<>("filterValue added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	

}
