package zkart.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import zkart.entity.Item;
import zkart.service.ItemService;
import zkart.service.StorageService;

@RestController
@RequestMapping(path="/item")
public class ItemController {
	
	@Autowired
    private StorageService storageService;
	
	@Autowired
	private ItemService itemService;
		
	@RequestMapping(method = RequestMethod.POST, value = "/addItem")
	public ResponseEntity<String> addItem(@RequestParam(value = "formData", required = false) String formData,@RequestParam("file") MultipartFile file) {
		boolean res=itemService.addItem(file,formData);
		if(res==true) {
			return new ResponseEntity<>("item added",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("There is an error while addding an item",HttpStatus.BAD_REQUEST);
		}
		//storageService.uploadFile(file,formData);
	}
	
	@RequestMapping()
	public ResponseEntity<ArrayList<Item>> getZkartItems(){
		return new ResponseEntity<>(itemService.getZkartItems(),HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Item> getZkartItemById(@PathVariable("id") int id){
		return new ResponseEntity<>(itemService.getZkartItemById(id),HttpStatus.OK);
	}
	
	@RequestMapping("/item/{id}")
	public ResponseEntity<Item> getZkartItemByItemId(@PathVariable("id") String itemId){
		return new ResponseEntity<>(itemService.getZkartItemByItemId(itemId),HttpStatus.OK);
	}
	//@RequestMapping("/date/{id}")
	
	@RequestMapping(method=RequestMethod.PUT,value="/update/{id}")
	public ResponseEntity<String> updateZkartItem(@PathVariable("id") int id,@RequestBody Item item){
		boolean res=itemService.updateZkartItem(id,item);
		if(res==true) {
			return new ResponseEntity<>("updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}")
	public ResponseEntity<String> deleteZkartItem(@PathVariable("id") Integer id){
		boolean res=itemService.deleteZkartItem(id);
		if(res==false) {
			return new ResponseEntity<>("updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/subcategoryid/{id}")
	public ResponseEntity<ArrayList<Item>> getZkartItemBySubcategoryId(@PathVariable("id") Integer id){
		return new ResponseEntity<>(itemService.getAllItemsBySubcategoryId(id),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/category/{id}")
	public ResponseEntity<ArrayList<Item>> getZkartItemsByCategoryId(@PathVariable("id") Integer id){
		return new ResponseEntity<>(itemService.getAllItemsByCategoryId(id),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/seller/{id}")
	public ResponseEntity<ArrayList<Item>> getZkartItemsBySellerId(@PathVariable("id") Integer id){
		return new ResponseEntity<>(itemService.getZkartItemsBySellerId(id),HttpStatus.OK);
	}
	

}
