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

import zkart.entity.Dealitems;
import zkart.service.DealItemsService;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/dealItems")
public class DealItemsController {
	@Autowired
	private DealItemsService dealItemService;
	
	@RequestMapping(method=RequestMethod.POST,value="/addItem")
	public ResponseEntity<String> addZkartDealItems(@RequestBody Dealitems dealItem){
		if(dealItemService.addZkartDealItems(dealItem)==true) {
			return new ResponseEntity<>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping()
	public ResponseEntity<ArrayList<Dealitems>> getZkartDealItems() {
			return new ResponseEntity<>(dealItemService.getAllDealItems(),HttpStatus.OK);
	}

}
