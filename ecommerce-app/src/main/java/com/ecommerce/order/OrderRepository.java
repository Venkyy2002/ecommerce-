package com.ecommerce.order;

import com.ecommerce.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    public void saveOrder(Order order) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);
        try {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orders (user_email, total) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getUserEmail());
            ps.setDouble(2, order.getTotal());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int orderId = keys.getInt(1);

            PreparedStatement itemPs = con.prepareStatement(
                "INSERT INTO order_items (order_id, product_id, product_name, price, quantity) VALUES (?, ?, ?, ?, ?)");
            for (OrderItem item : order.getItems()) {
                itemPs.setInt(1, orderId);
                itemPs.setInt(2, item.getProductId());
                itemPs.setString(3, item.getProductName());
                itemPs.setDouble(4, item.getPrice());
                itemPs.setInt(5, item.getQuantity());
                itemPs.addBatch();
            }
            itemPs.executeBatch();
            con.commit();

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    public List<Order> getOrdersByEmail(String email) throws Exception {
        Connection con = DBConnection.getConnection();
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, user_email, total, created_at FROM orders WHERE user_email = ? ORDER BY created_at DESC");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            Map<Integer, Order> orderMap = new LinkedHashMap<>();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUserEmail(rs.getString("user_email"));
                o.setTotal(rs.getDouble("total"));
                o.setCreatedAt(rs.getString("created_at"));
                o.setItems(new ArrayList<>());
                orderMap.put(o.getId(), o);
                orders.add(o);
            }
            if (!orderMap.isEmpty()) {
                String placeholders = String.join(",", Collections.nCopies(orderMap.size(), "?"));
                PreparedStatement itemPs = con.prepareStatement(
                    "SELECT order_id, product_id, product_name, price, quantity FROM order_items WHERE order_id IN (" + placeholders + ")");
                int idx = 1;
                for (int oid : orderMap.keySet()) itemPs.setInt(idx++, oid);
                ResultSet itemRs = itemPs.executeQuery();
                while (itemRs.next()) {
                    OrderItem item = new OrderItem();
                    item.setProductId(itemRs.getInt("product_id"));
                    item.setProductName(itemRs.getString("product_name"));
                    item.setPrice(itemRs.getDouble("price"));
                    item.setQuantity(itemRs.getInt("quantity"));
                    orderMap.get(itemRs.getInt("order_id")).getItems().add(item);
                }
            }
        } finally {
            con.close();
        }
        return orders;
    }
}
