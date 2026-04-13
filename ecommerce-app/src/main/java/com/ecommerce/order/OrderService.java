package com.ecommerce.order;

import java.util.List;

public class OrderService {

	private OrderRepository repo = new OrderRepository();

	public String placeOrder(Order order) {
		if (order.getItems() == null || order.getItems().isEmpty())
			return "EMPTY_CART";
		if (order.getUserEmail() == null || order.getUserEmail().isEmpty())
			return "INVALID_USER";

		double total = 0;
		for (OrderItem item : order.getItems()) {
			total += item.getPrice() * item.getQuantity();
		}
		order.setTotal(total);

		try {
			repo.saveOrder(order);
			return "ORDER_PLACED";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	public List<Order> getOrdersByEmail(String email) throws Exception{
		 return repo.getOrdersByEmail(email);
	}

}
