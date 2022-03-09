package com.icreon.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.icreon.demo.entities.Users;



@Component
public class UserDetailsDao {
	Statement st;
	PreparedStatement pst;
	Connection con;

	public UserDetailsDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Statement getStatement() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "root");
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	
	
	public PreparedStatement getPreparedStatement(String query) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "root");
			pst = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pst;
	}
	
	

	public void update(String username, String role) {
		try {
			PreparedStatement pst = this.getPreparedStatement("update users set user_type=? where name=?");
			pst.setString(1, role);
			pst.setString(2, username);
// 4.FIre quesries
			pst.execute(); // 5.close connection
			con.close();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Users> getAll() {
		List<Users> list = new ArrayList<Users>();
		try {
			Statement st = this.getStatement(); // 4.FIre quesries
			ResultSet rs = st.executeQuery("select * from users");
			while (rs.next()) {
				Users u = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("user_type"));
				list.add(u);
			} // 5.close connection
			con.close();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Users> getRequests(){
		List<Users> list = new ArrayList<Users>();
		
		try {
			Statement st = this.getStatement();
			ResultSet rs = st.executeQuery("select * from users where user_type= 'Null'");
			while(rs.next()) {
				Users u = new Users(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("user_type"));
				list.add(u);		
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public void delete(String id, String username) {
		try {
			PreparedStatement pst = this.getPreparedStatement("delete from users where id = ? and name = ?");
			pst.setString(1, id);
			pst.setString(2, username);
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UserDeleteByAdmin(String role, String username) {
		try {
			PreparedStatement pst = this.getPreparedStatement("delete from users where user_type = ? and name = ?");
			pst.setString(1, role);
			pst.setString(2, username);
			pst.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
