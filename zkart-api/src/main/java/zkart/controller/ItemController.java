package zkart.controller;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	public void addItem(@RequestParam(value = "formData", required = false) String formData,@RequestParam("file") MultipartFile file) {
		String fname=file.getOriginalFilename();
		itemService.addItem(formData);
		storageService.uploadFile(file,formData);
		return;
	}
	
}
