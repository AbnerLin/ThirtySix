package com.thirtySix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USERROLE")
public class UserRole implements Serializable {

	private static final long serialVersionUID = -946066744230276370L;

	/**
	 * User info.
	 */
	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
	private UserInfo user;

	/**
	 * User role.
	 */
	@Id
	@Column(name = "ROLE")
	private String role;

	/**
	 * Get User object.
	 * 
	 * @return
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * Set User object.
	 * 
	 * @param user
	 */
	public void setUser(UserInfo user) {
		this.user = user;
	}

	/**
	 * Get user's role.
	 * 
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Set user's role.
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
