package zkart.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import zkart.dto.ItemDTO;
import zkart.dto.Pair;
import zkart.entity.Item;
import zkart.entity.ItemDetails;
import zkart.entity.ItemFilters;
import zkart.service.ItemDetailsService;
import zkart.service.ItemFiltersService;
import zkart.service.ItemService;
import zkart.service.StorageService;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping(path="/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
		
	@RequestMapping(method = RequestMethod.POST, value = "/addItem")
	public ResponseEntity<String> addItem(@RequestParam(value = "formData", required = false) String formData,@RequestParam("file") MultipartFile file) {
		String res=itemService.addItem(file,formData);
			return new ResponseEntity<>(res,HttpStatus.OK);
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
		
		System.out.println(itemId);
		return new ResponseEntity<>(itemService.getZkartItemByItemId(itemId),HttpStatus.OK);
	}
	@RequestMapping("/date/{id}")
	public ResponseEntity<ArrayList<Item>> getFlopkartListingByDate(@PathVariable("id") int id) {
		return new ResponseEntity<>(itemService.getFlopkartListingsSortedByDate(id),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/update/{id}")
	public ResponseEntity<String> updateZkartItem(@PathVariable("id") int id,@RequestBody Item item){
		boolean res=itemService.updateZkartItem(id,item);
		if(res==true) {
			return new ResponseEntity<>("updated",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/update/quantity/{id}")
	public ResponseEntity<String> updateItemQuantity(@PathVariable("id") int id,@RequestBody Item item){
		Item itemDetails = itemService.getZkartItemById(id);
		itemDetails.setQuantity(item.getQuantity());
		boolean res = itemService.updateZkartItem(id, itemDetails);
		if(res)
			return new ResponseEntity<>("updated",HttpStatus.OK);
		return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}")
	public ResponseEntity<String> deleteZkartItem(@PathVariable("id") Integer id){
		boolean res=itemService.deleteZkartItem(id);
		if(res==true) {
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
	
	@GetMapping("/itemDTO/subCategoryId/{subCategoryId}")
	public ResponseEntity<ArrayList<ItemDTO>> getItemDTOBySubcategoryId(@PathVariable("subCategoryId") Integer subCategoryId){
		return new ResponseEntity<>(itemService.getItemDTOsBySubCategoryId(subCategoryId), HttpStatus.OK);
	}
}
