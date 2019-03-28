package zkart.entity;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Deal {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String description;
	private String startDate;
	private String endDate;
	private Integer discount;
	
	public Integer getId() {
		return id;
	}
	public Deal() {
		
	}
	public Deal(Integer id, String description, Integer discount) {
		super();
		this.id = id;
		this.description = description;
		this.discount = discount;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Deal [id=" + id + ", description=" + description + ", discount=" + discount + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
