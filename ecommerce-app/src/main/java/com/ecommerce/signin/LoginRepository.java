package com.ecommerce.signin;

import com.ecommerce.utils.DBConnection;
import java.sql.*;

public class LoginRepository {

	public Login findByEmail(String email) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id, email, password, role FROM users WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Login user = new Login();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPasswordHash(rs.getString("password"));
				user.setRole(rs.getString("role"));
				con.close();
				return user;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
