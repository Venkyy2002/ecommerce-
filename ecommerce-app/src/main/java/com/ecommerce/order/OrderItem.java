package com.ecommerce.order;

public class OrderItem {

	private int productId;
	private String productName;
	private double price;
	private int quantity;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int v) {
		this.productId = v;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String v) {
		this.productName = v;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double v) {
		this.price = v;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int v) {
		this.quantity = v;
	}
}
