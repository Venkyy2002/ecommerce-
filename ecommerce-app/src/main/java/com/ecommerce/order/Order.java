package com.ecommerce.order;

import java.util.List;

public class Order {

	private int id;
	private String createdAt;
	private String userEmail;
	private double total;
	private List<OrderItem> items;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String v) {
		this.userEmail = v;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double v) {
		this.total = v;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> v) {
		this.items = v;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
