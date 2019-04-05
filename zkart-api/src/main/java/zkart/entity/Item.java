package zkart.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity 
@Table(name="item")
public class Item{
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String listingName;
	private String imgUrl;
	
	@Column(unique=true)
	private String itemId;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="SUBCATEGORYID", referencedColumnName="ID")
    })
	private SubCategory subCategory;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="SELLERID", referencedColumnName="ID")
    })
	private User user;
	
	
	@OneToMany(targetEntity=ItemFilters.class, mappedBy="item",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ItemFilters> itemFilters;
	
	
	private Integer quantity;
	private Integer price;
	private String colour;
	private String brand;
	private String manufacture_Date;
	private String description;
	private String priority;
	private Integer discount;
	private Integer bdaydiscount;

	public Item() {
		super();
	}

	public Set<ItemFilters> getItemFilters() {
		return itemFilters;
	}

	public void setItemFilters(Set<ItemFilters> itemFilters) {
		this.itemFilters = itemFilters;
	}

	public Integer getBdaydiscount() {
		return bdaydiscount;
	}

	public void setBdaydiscount(Integer bdaydiscount) {
		this.bdaydiscount = bdaydiscount;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getId() {
		return id;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getListingName() {
		return listingName;
	}

	public void setListingName(String listingName) {
		this.listingName = listingName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getManufacture_Date() {
		return manufacture_Date;
	}

	public void setManufacture_Date(String manufacture_Date) {
		this.manufacture_Date = manufacture_Date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", listingName=" + listingName + ", imgUrl=" + imgUrl + ", itemId="
				+ itemId +  ", quantity=" + quantity
				+ ", price=" + price + ", colour=" + colour + ", brand=" + brand + ", manufacture_Date="
				+ manufacture_Date + ", description=" + description + ", priority=" + priority + ", discount="
				+ discount + ", bdaydiscount=" + bdaydiscount + "]";
	}
	
}
