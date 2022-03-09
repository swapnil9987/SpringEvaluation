package com.icreon.demo.entities;

import java.sql.*;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Users {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String userType;

	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(Integer id, String name, String email, String password, String userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public Users(String name, String email, String password, String userType) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", userType="
				+ userType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(id, other.id);
	}

}
