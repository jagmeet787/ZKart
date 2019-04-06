package zkart.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Filter;
import zkart.entity.FilterValues;
import zkart.repository.FilterValuesRepository;

@Service
public class FilterValuesService {
	@Autowired
	private FilterValuesRepository filterValuesRepository;

	
	public ArrayList<FilterValues> getZkartFilterValues(){
		ArrayList<FilterValues> filterValues=new ArrayList<FilterValues>();
		Iterable<FilterValues> iterable=filterValuesRepository.findAll();
		Iterator<FilterValues> iterator=iterable.iterator();
		while(iterator.hasNext()) {
			filterValues.add(iterator.next());
		}
		return filterValues;
	}

	public ArrayList<FilterValues> getZkartFilterValuesByFilterId(Integer filterId){
		ArrayList<FilterValues> filterValues=new ArrayList<FilterValues>();
		ArrayList<FilterValues> result=new ArrayList<FilterValues>();
		filterValues = getZkartFilterValues();
		for(FilterValues i : filterValues)
		{
			System.out.println(i.getFilter().getId());
			if(i.getFilter().getId().equals(filterId))
				result.add(i);
		}
		return result;
	}

	public boolean addZkartFilterValues(Integer filterId,String value) {
		boolean res=false;
		try {
			FilterValues filterValues = new FilterValues();
			filterValues.setFilter(new Filter(filterId, "", null));
			filterValues.setValue(value);
			filterValuesRepository.save(filterValues);
			res=true;
			
		}catch(Exception e) {
			res=false;
			System.out.println(e);
		}
		return res;
	}
	

}
