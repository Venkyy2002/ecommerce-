package com.ecommerce.signup;

public class User {

	private String name;
	private String email;
	private String passwordHash;
	private String role;

	public String getName() {
		return name;
	}

	public void setName(String v) {
		this.name = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		this.email = v;
	}

	public String getPassword() {
		return passwordHash;
	}

	public void setPassword(String v) {
		this.passwordHash = v;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String v) {
		this.role = v;
	}
}
