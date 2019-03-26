package zkart.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Deal;
import zkart.entity.Dealitems;
import zkart.repository.DealRepository;

@Service
public class DealService {
	
	@Autowired
	private DealRepository dealRepository;
	
	@Autowired
	private DealItemsService dealItemsService;
	
	public boolean addZkartDeal(Deal deal) {
		boolean res=true;
		try {
			dealRepository.save(deal);
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	
	public ArrayList<Deal> getZkartDeals(){
		ArrayList<Deal> deals=new ArrayList<Deal>();
		dealRepository.findAll().forEach(deals::add);
		return deals;
	}
	
	public Deal getZkartById(Integer id) {
		return dealRepository.findById(id).get();
	}
	
	public ArrayList<Deal> getZkartDealsByItemId(Integer itemId) {
		ArrayList<Deal> deals=new ArrayList<>();
		try {
			HashSet<Integer> set=new HashSet<Integer>();
			ArrayList<Dealitems> dealitems=dealItemsService.getAllItemsByItemId(itemId);
			for(Integer i=0;i<dealitems.size();i++) {
				if(dealitems.get(i).getItem().getId()==itemId) {
					set.add(dealitems.get(i).getDeal().getId());
				}
			}
			Iterator<Integer> dids=set.iterator();
			while(dids.hasNext()) {
				deals.add(dealRepository.findById(dids.next()).get());
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return deals;
	}
	
	public ArrayList<Deal> getZkartDealsBySellerId(Integer sellerId){
		ArrayList<Deal> deals=new ArrayList<>();
		try {
			HashSet<Integer> set=new HashSet<Integer>();
			ArrayList<Dealitems> dealitems=dealItemsService.getAllItemsBySellerId(sellerId);
			for(Integer i=0;i<dealitems.size();i++) {
				if(dealitems.get(i).getUser().getId()==sellerId) {
					set.add(dealitems.get(i).getDeal().getId());
				}
			}
			Iterator<Integer> dids=set.iterator();
			while(dids.hasNext()) {
				deals.add(dealRepository.findById(dids.next()).get());
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return deals;
	}
}
