package zkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import zkart.entity.Item;
import zkart.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public boolean addItem(String formData) {
		Item item=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			item = mapper.readValue(formData, Item.class);
			System.out.println(item);
		}catch(Exception e) {
			System.out.println(e);
		}
		return itemRepository.save(item)!=null;
	}
}
