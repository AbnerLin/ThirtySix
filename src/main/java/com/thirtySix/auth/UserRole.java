package com.thirtySix.auth;

import java.util.Arrays;

/**
 * User Privilege.
 */
public enum UserRole {
	ROLE_ADMIN(0), ROLE_STAFF(1);

	private Integer level;

	UserRole(final Integer level) {
		this.level = level;
	}

	public Integer value() {
		return this.level;
	}

	public static UserRole valueOfByDesc(final Integer level) {
		return Arrays.asList(values()).stream().filter(role -> {
			return role.value() == level;
		}).findFirst().orElse(null);
	}
}
