package zkart.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import zkart.entity.Account;
import zkart.entity.Item;
import zkart.entity.SubCategory;
import zkart.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UserService userService;
	
	public String addItem(MultipartFile file,String formData) {
		Item item=null;
		String res="-1";
		try {
			ObjectMapper mapper = new ObjectMapper();
			item = mapper.readValue(formData, Item.class);
			item=itemRepository.save(item);
			item.setImgUrl(item.getId()+".jpeg");
			item=itemRepository.save(item);
			res=item.getId().toString();
			System.out.println("item saved");
			storageService.uploadFile(file,item.getId().toString()+".jpeg");
		}catch(Exception e) {
			res="-1";
			System.out.println("error"+e);
		}
		return res;
	}
	public ArrayList<Item> getZkartItems(){
		ArrayList<Item> items=new ArrayList<Item>();
		//itemRepository.findAll().forEach(items::add);
		Iterable<Item> iterable=itemRepository.findAll();
		Iterator<Item> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			items.add(iterator.next());
		}
		return items;	
	}
	
	public ArrayList<Item> getFlopkartListingsSortedByDate(Integer id)
	{
		ArrayList<Item> items=new ArrayList<Item>();
		Iterable<Item> iterable=itemRepository.findAll();
		Iterator<Item> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			items.add(iterator.next());
		}
		return items;
	}
	
	public Item getZkartItemById(Integer id) {
		return itemRepository.findById(id).get();
	}
	
	public Item getZkartItemByItemId(String itemId) {
		return itemRepository.findAllByItemId(itemId);
	}
	
	public Boolean updateZkartItem(Integer id,Item item) {
		boolean res=true;
		item.setId(id);
		try {
			itemRepository.save(item);
		}catch(Exception e) {
			res=false;
		}
		return res;
	}
	public Boolean deleteZkartItem(Integer id) {
		boolean res=true;
		try {
			itemRepository.deleteById(id);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	public ArrayList<Item> getAllItemsBySubcategoryId(Integer subcategoryId){
		return itemRepository.findAllBySubCategoryId(subcategoryId);
	}
	
	public ArrayList<Item> getAllItemsByCategoryId(Integer categoryId){
		ArrayList<Item> res=new ArrayList<Item>();
		try {
			ArrayList<SubCategory> subCategories=subCategoryService.getZkartSubCategoryByCategoryId(categoryId);
			for(SubCategory subCategory:subCategories) {
				res.addAll(getAllItemsBySubcategoryId(subCategory.getId()));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}
	public ArrayList<Item> getZkartItemsBySellerId(Integer sellerId){
		return itemRepository.findAllByUserId(sellerId);
	}

}
