package com.thirtySix.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	public static void main(final String[] args) {

		final String password = "user02";
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);

	}
}
