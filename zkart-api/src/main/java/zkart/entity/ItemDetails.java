package zkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class ItemDetails {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ID;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="ITEMID", referencedColumnName="ID")
    })
	private Item item;
	private String attr_name;
	private String attr_val;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}


	public String getAttr_name() {
		return attr_name;
	}

	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getAttr_val() {
		return attr_val;
	}

	public void setAttr_val(String attr_val) {
		this.attr_val = attr_val;
	}
}
