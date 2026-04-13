package com.ecommerce.cart;

import java.util.List;

public class CartService {

	private CartRepository repo = new CartRepository();

	public String addItem(Cart item) {
		if (item.getUserEmail() == null || item.getUserEmail().isEmpty())
			return "INVALID_USER";
		if (item.getProductId() <= 0)
			return "INVALID_PRODUCT";
		if (item.getQuantity() <= 0)
			item.setQuantity(1);

		try {
			repo.addItem(item);
			return "ADDED";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	public String updateQuantity(int cartId, int quantity) {
		try {
			if (quantity <= 0) {
				repo.removeItem(cartId);
				return "REMOVED";
			}
			repo.updateQuantity(cartId, quantity);
			return "UPDATED";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	public String removeItem(int cartId) {
		try {
			repo.removeItem(cartId);
			return "REMOVED";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	public List<Cart> getCart(String userEmail) {
		try {
			return repo.getCart(userEmail);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String clearCart(String userEmail) {
		try {
			repo.clearCart(userEmail);
			return "CLEARED";
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
}
