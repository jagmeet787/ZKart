package zkart.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Deal;
import zkart.entity.Dealitems;
import zkart.repository.DealItemsRepository;

@Service
public class DealItemsService {
	
	@Autowired
	private DealItemsRepository dealItemsRepository;
	
	public boolean addZkartDealItems(Dealitems dealItem) {
		boolean res=true;
		try {
			dealItemsRepository.save(dealItem);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	public ArrayList<Dealitems> getAllItemsBySellerId(Integer sellerId) {
		return dealItemsRepository.findAllByUserId(sellerId);
	}
	public ArrayList<Dealitems> getAllItemsByItemId(Integer itemId){
		return dealItemsRepository.findAllByItemId(itemId);
	}
	public ArrayList<Dealitems> getAllItemsByDealId(Integer dealId){
		return dealItemsRepository.findAllByDealId(dealId);
	}
	public ArrayList<Dealitems> getAllDealItems(){
		ArrayList<Dealitems> deals=new ArrayList<Dealitems>();
		dealItemsRepository.findAll().forEach(deals::add);
		return deals;
	}
}
