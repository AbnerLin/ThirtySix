package com.thirtySix.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USERINFO")
public class UserInfo implements UserDetails {

	private static final long serialVersionUID = -8235169970103543613L;

	/**
	 * User name.
	 */
	@Id
	@Column(name = "USERNAME")
	private String userName;

	/**
	 * Password.
	 */
	@Column(name = "PASSWORD", nullable = false)
	private String passWord;

	/**
	 * User's role.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserRole> roleList;

	/**
	 * Get user name.
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set user name.
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get password.
	 * 
	 * @return
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * Set password
	 * 
	 * @param passWord
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * Get role of Users.
	 * 
	 * @return
	 */
	public List<UserRole> getRoleList() {
		return roleList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();

		List<UserRole> userRole = this.getRoleList();
		userRole.forEach(role -> {
			autorities.add(new SimpleGrantedAuthority(role.getRole()));
		});

		return autorities;
	}

	@Override
	public String getPassword() {
		return this.getPassWord();
	}

	@Override
	public String getUsername() {
		return this.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
