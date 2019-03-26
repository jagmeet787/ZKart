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
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="USERID", referencedColumnName="ID")
    })
	private User user; //
	private String itemId;
	private Integer orderId;
	private String shippingAddress;
	private String orderStatus;
	private String orderDate;
	private String recievedDate;
	private String returnDate;
	private Integer totalAmount;
	private Integer quantity;
	private Integer buyerAccountNo;
	
	public Orders() {
	}

	public Orders(Integer id, String shippingAddress, User user, String itemId, Integer orderId, String status,
			String orderDate, String recievedDate, String returnDate, Integer totalAmount, Integer quantity,
			Integer buyerAccountNo) {
		this.id = id;
		this.shippingAddress = shippingAddress;
		this.user = user;
		this.itemId = itemId;
		this.orderId = orderId;
		this.orderStatus = status;
		this.orderDate = orderDate;
		this.recievedDate = recievedDate;
		this.returnDate = returnDate;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
		this.buyerAccountNo = buyerAccountNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(String recievedDate) {
		this.recievedDate = recievedDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getBuyerAccountNo() {
		return buyerAccountNo;
	}

	public void setBuyerAccountNo(Integer buyerAccountNo) {
		this.buyerAccountNo = buyerAccountNo;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", shippingAddress=" + shippingAddress + ", user=" + user + ", itemId=" + itemId
				+ ", orderId=" + orderId + ", status=" + orderStatus + ", orderDate=" + orderDate + ", recievedDate="
				+ recievedDate + ", returnDate=" + returnDate + ", totalAmount=" + totalAmount + ", quantity="
				+ quantity + ", buyerAccountNo=" + buyerAccountNo + "]";
	}
		
}
