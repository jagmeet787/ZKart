package zkart.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class ItemFilters {
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="ITEMID", referencedColumnName="ID")
    })
	private Item item;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="FILTERID", referencedColumnName="ID")
    }) 
	private FilterValues filterValues;
	public ItemFilters() {
		
	}
	public ItemFilters(Integer id, Item item, FilterValues filterValues) {
		super();
		this.id = id;
		this.item = item;
		this.filterValues = filterValues;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public FilterValues getFilterValues() {
		return filterValues;
	}

	public void setFilterValues(FilterValues filterValues) {
		this.filterValues = filterValues;
	}

	@Override
	public String toString() {
		return "ItemFilters [id=" + id + ", item=" + item + ", filterValues=" + filterValues + "]";
	}
	
	
	

}
