package com.ecommerce.signin;

public class Login {

	private int id;
	private String email;
	private String passwordHash;
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int v) {
		this.id = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		this.email = v;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String v) {
		this.passwordHash = v;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String v) {
		this.role = v;
	}
}
