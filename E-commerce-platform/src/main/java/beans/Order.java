package beans;

import java.util.Date;

public class Order {

	private int orderId;
	private String productName;
	private String productImgPath;
	private Date orderDate;
	private String userName;

	public Order(int orderId, String productName, String productImgPath, Date orderDate, String userName) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.productImgPath = productImgPath;
		this.orderDate = orderDate;
		this.userName = userName;
	}

	public Order() {
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productName=" + productName + ", productImgPath=" + productImgPath
				+ ", orderDate=" + orderDate + ", userName=" + userName + "]";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImgPath() {
		return productImgPath;
	}

	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
