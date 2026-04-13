package com.ecommerce.product;

import com.ecommerce.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

	public List<Product> findAll(String category, String search) throws Exception {
		List<Product> list = new ArrayList<>();

		StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
		if (category != null && !category.isEmpty())
			sql.append(" AND category = ?");
		if (search != null && !search.isEmpty())
			sql.append(" AND name LIKE ?");

		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(sql.toString());

		int idx = 1;
		if (category != null && !category.isEmpty())
			ps.setString(idx++, category);
		if (search != null && !search.isEmpty())
			ps.setString(idx++, "%" + search + "%");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setCategory(rs.getString("category"));
			p.setPrice(rs.getDouble("price"));
			list.add(p);
		}

		con.close();
		return list;
	}
}
