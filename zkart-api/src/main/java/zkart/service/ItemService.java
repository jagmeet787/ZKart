package zkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import zkart.entity.Item;
import zkart.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private StorageService storageService;
	
	public boolean addItem(MultipartFile file,String formData) {
		Item item=null;
		boolean res=true;
		try {
			ObjectMapper mapper = new ObjectMapper();
			item = mapper.readValue(formData, Item.class);
			item=itemRepository.save(item);
			storageService.uploadFile(file, item.getItemId().toString());
			return true;
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
}
