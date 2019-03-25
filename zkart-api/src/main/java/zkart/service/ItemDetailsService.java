package zkart.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.bytecode.Descriptor.Iterator;
import zkart.entity.ItemDetails;
import zkart.entity.User;
import zkart.repository.ItemDetailsRepository;

@Service
public class ItemDetailsService {
	@Autowired
	private ItemDetailsRepository itemDetailsRepository;
	
	public boolean addZkartItemDetails(ItemDetails item) {
		boolean res=true;
		try {
			itemDetailsRepository.save(item);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	
	public ArrayList<ItemDetails> getZkartItemDetails(Integer itemId){
		ArrayList<ItemDetails> list = new ArrayList<ItemDetails>();
		itemDetailsRepository.findAllByItemId(itemId).forEach(list::add);
		return list;
	}
}
