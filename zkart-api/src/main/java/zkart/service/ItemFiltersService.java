package zkart.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.dto.Pair;
import zkart.entity.Category;
import zkart.entity.ItemFilters;
import zkart.repository.ItemFiltersRepository;


@Service
public class ItemFiltersService {
	
	
	@Autowired
	private ItemFiltersRepository itemFiltersRepository;
	
	
	public boolean addZkartItemFilter(ItemFilters itemFilter) {
		boolean res=true;
		try {
			System.out.println("item filter:"+itemFilter);
			itemFiltersRepository.save(itemFilter);
		}catch(Exception e) {
			System.out.println(e);
			res=false;
		}
		return res;
	}
	
	public ArrayList<ItemFilters> getAllZkartItemFilters() {
		ArrayList<ItemFilters> itemFilters=new ArrayList<ItemFilters>();
		Iterable<ItemFilters> iterable=itemFiltersRepository.findAll();
		Iterator<ItemFilters> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			itemFilters.add(iterator.next());
		}
		return itemFilters;
	}

	public ArrayList<ItemFilters> getAllItemFiltersByItemId(Integer itemId) {
		return itemFiltersRepository.findByItemId(itemId);
	}
}
