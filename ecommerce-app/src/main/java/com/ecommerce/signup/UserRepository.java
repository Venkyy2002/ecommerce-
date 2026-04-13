package com.ecommerce.signup;

import com.ecommerce.utils.DBConnection;
import java.sql.*;

public class UserRepository {

	public void insertUser(User user) throws Exception {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, email, password) VALUES (?, ?, ?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		} finally {
			con.close();
		}
	}

	public User findByEmail(String email) throws Exception {
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			return null;
		} finally {
			con.close();
		}
	}
}
