package com.ecommerce.product;

public class Product {

	private int id;
	private String name;
	private String category;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int v) {
		this.id = v;
	}

	public String getName() {
		return name;
	}

	public void setName(String v) {
		this.name = v;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String v) {
		this.category = v;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double v) {
		this.price = v;
	}

}
