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
import zkart.entity.ItemDetails;
import zkart.service.ItemDetailsService;
@CrossOrigin( origins = "*" )
@RestController
@RequestMapping(path="/details")
public class ItemDetailsController {
	@Autowired
	private ItemDetailsService itemDetailsService;
	@RequestMapping(method = RequestMethod.POST, value = "/addItem")
	public ResponseEntity<String> addZkartItemDetails(@RequestBody ItemDetails itemDetails){
		if(itemDetailsService.addZkartItemDetails(itemDetails)==false) {
			return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>("success",HttpStatus.OK);
		}
	}
	@RequestMapping(method=RequestMethod.POST,value="/addItems")
	public ResponseEntity<String> addZkartMultipleItemDetails(@RequestBody ArrayList<ItemDetails> itemDetails){
		Boolean res=true;
		for(Integer i=0;i<itemDetails.size();i++) {
			System.out.println(i);
			//System.out.println(itemDetails.get(i));
			res=itemDetailsService.addZkartItemDetails(itemDetails.get(i));
			System.out.println(res);
		}
		return new ResponseEntity<>(res.toString(),HttpStatus.OK);
	}
	@RequestMapping("/itemid/{id}")
	public ResponseEntity<ArrayList<ItemDetails>> getZkartItemDetails(@PathVariable("id") int id){
		return new ResponseEntity<>(itemDetailsService.getZkartItemDetails(id),HttpStatus.OK);
	}
	@RequestMapping(value = "/delete/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteByItemId(@PathVariable Integer itemId) {
		itemDetailsService.deleteByItemId(itemId);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}
