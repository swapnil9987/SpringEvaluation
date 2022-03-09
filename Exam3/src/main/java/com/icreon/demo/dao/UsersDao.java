package com.icreon.demo.dao;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.icreon.demo.entities.Users;

@Service
public class UsersDao {

	@Autowired
	Users users;

	private static Connection con;

	public UsersDao(Connection con) {
		this.con = con;
	}

	public UsersDao() {

	}

	public static void addUser(Users users) {

		try {
			String query = "insert into users(name, email, password, user_type) values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, users.getName());
			pst.setString(2, users.getEmail());
			pst.setString(3, users.getPassword());
			pst.setString(4, users.getUserType());

			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Users getUserByEmailAndPassword(String email, String password) {
		Users user = null;

		try {

			String query = "select * from users where email =? and password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				user = new Users();

//		             data from db
				String name = set.getString("name");
//		             set to user object
				user.setName(name);

				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setUserType(set.getString("user_type"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	public Users getUserByEmailAndPasswordAndUserType(String email, String password, String user_type) {
		Users user = null;

		try {

			String query = "select * from users where email =? and password=? and user_type = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setString(3, user_type);
			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				user = new Users();

//		             data from db
				String name = set.getString("name");
//		             set to user object
				user.setName(name);

				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setUserType(set.getString("user_type"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}
	
	public static Users getUserByEmailAndPassword1(String email) {
		Users user = null;

		try {

			String query = "select * from users where email =? ";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			//pstmt.setString(2, password);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {
				user = new Users();

//		             data from db
				String name = set.getString("name");
//		             set to user object
				user.setName(name);

				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setUserType(set.getString("user_type"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

}
