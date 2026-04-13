package com.ecommerce.cart;

import com.ecommerce.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {

	public void addItem(Cart item) throws Exception {
		Connection con = DBConnection.getConnection();
		try {
			String check = "SELECT id, quantity FROM cart WHERE user_email = ? AND product_id = ?";
			PreparedStatement ps = con.prepareStatement(check);
			ps.setString(1, item.getUserEmail());
			ps.setInt(2, item.getProductId());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int newQty = rs.getInt("quantity") + item.getQuantity();
				int cartId = rs.getInt("id");
				PreparedStatement ups = con.prepareStatement("UPDATE cart SET quantity = ? WHERE id = ?");
				ups.setInt(1, newQty);
				ups.setInt(2, cartId);
				ups.executeUpdate();
			} else {
				PreparedStatement ins = con.prepareStatement(
						"INSERT INTO cart (user_email, product_id, product_name, emoji, price, quantity) VALUES (?, ?, ?, ?, ?, ?)");
				ins.setString(1, item.getUserEmail());
				ins.setInt(2, item.getProductId());
				ins.setString(3, item.getProductName());
				ins.setDouble(5, item.getPrice());
				ins.setInt(6, item.getQuantity());
				ins.executeUpdate();
			}
		} finally {
			con.close();
		}
	}

	public void updateQuantity(int cartId, int quantity) throws Exception {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE cart SET quantity = ? WHERE id = ?");
			ps.setInt(1, quantity);
			ps.setInt(2, cartId);
			ps.executeUpdate();
		} finally {
			con.close();
		}
	}

	public void removeItem(int cartId) throws Exception {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM cart WHERE id = ?");
			ps.setInt(1, cartId);
			ps.executeUpdate();
		} finally {
			con.close();
		}
	}

	public List<Cart> getCart(String userEmail) throws Exception {
		List<Cart> list = new ArrayList<>();
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM cart WHERE user_email = ?");
			ps.setString(1, userEmail);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cart c = new Cart();
				c.setId(rs.getInt("id"));
				c.setUserEmail(rs.getString("user_email"));
				c.setProductId(rs.getInt("product_id"));
				c.setProductName(rs.getString("product_name"));
				c.setEmoji(rs.getString("emoji"));
				c.setPrice(rs.getDouble("price"));
				c.setQuantity(rs.getInt("quantity"));
				list.add(c);
			}
		} finally {
			con.close();
		}
		return list;
	}

	public void clearCart(String userEmail) throws Exception {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM cart WHERE user_email = ?");
			ps.setString(1, userEmail);
			ps.executeUpdate();
		} finally {
			con.close();
		}
	}
}
