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

import zkart.entity.Deal;
import zkart.service.DealService;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping("/deals")
public class DealController {
	@Autowired
	private DealService dealService;

	@CrossOrigin( origins = "*" )
	@RequestMapping(method=RequestMethod.POST,value="/create")
	ResponseEntity<String> addZkartDeal(@RequestBody Deal deal){
		System.out.println(deal);
		if(dealService.addZkartDeal(deal)==true) {
			return new ResponseEntity<>("success",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin( origins = "*" )
	@RequestMapping()
	ResponseEntity<ArrayList<Deal>> getZkartDeals(){
		return new ResponseEntity<>(dealService.getZkartDeals(),HttpStatus.OK);
	}
	
	@CrossOrigin( origins = "*" )
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	ResponseEntity<Deal> getZkartDealsById(@PathVariable Integer id){
		return new ResponseEntity<>(dealService.getZkartById(id),HttpStatus.OK);
	}
	
	@CrossOrigin( origins = "*" )
	@RequestMapping(method=RequestMethod.GET,value="/item/{id}")
	ResponseEntity<ArrayList<Deal>> getZkartDealByItemId(@PathVariable Integer id){
		return new ResponseEntity<>(dealService.getZkartDealsByItemId(id),HttpStatus.OK);
	}
	
	@CrossOrigin( origins = "*" )
	@RequestMapping(method=RequestMethod.GET,value="/seller/{id}")
	ResponseEntity<ArrayList<Deal>> getZkartDealsBySellerId(@PathVariable("id") Integer sellerId){
		return new ResponseEntity<>(dealService.getZkartDealsBySellerId(sellerId),HttpStatus.OK);
	}
}
