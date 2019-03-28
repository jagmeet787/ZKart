package zkart.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "USERID", referencedColumnName = "ID") })
	private User user;
	private String itemId;
	private Integer quantity;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "DEALID", referencedColumnName = "ID") })
	private Deal deal;

	public Cart() {
	}

	public Cart(Integer id, User user, String itemId, Integer quantity) {
		this.id = id;
		this.user = user;
		this.itemId = itemId;
		this.quantity = quantity;
	}
	
	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", itemId=" + itemId + ", quantity=" + quantity + "]";
	}
	
}
