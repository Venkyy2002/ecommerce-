package com.ecommerce.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

	private static final String SECRET = "fdnqz`5f3itn`fnbzisf3dplf`usq";
	private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	public static String generateToken(String email, String role) {
		return Jwts.builder().setSubject(email).claim("role", role).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)).signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	public static Claims validateToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public static String getEmail(String token) {
		return validateToken(token).getSubject();
	}

	public static String getRole(String token) {
		return (String) validateToken(token).get("role");
	}
}
