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
public class Dealitems {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="DEALID", referencedColumnName="ID")
    })
	private Deal deal;
	
	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="USERID", referencedColumnName="ID")
    })
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="ITEMID", referencedColumnName="ID")
    })
	private Item item;

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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Deal [id=" + id + ", user=" + user + ", item=" + item + "]";
	}
}