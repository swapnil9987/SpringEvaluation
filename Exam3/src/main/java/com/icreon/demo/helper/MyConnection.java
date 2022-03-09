package com.icreon.demo.helper;

import java.sql.*;

import org.springframework.stereotype.Service;
@Service
public class MyConnection {
	
	public static Connection con;

	public MyConnection() {

	}

	public static Connection getMyConnection() {

		try {
			if (con == null) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "root");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}
}
