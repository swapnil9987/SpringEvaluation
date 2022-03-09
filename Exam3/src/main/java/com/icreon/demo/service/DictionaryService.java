package com.icreon.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icreon.demo.dao.UserDetailsDao;
import com.icreon.demo.dao.UsersDao;
import com.icreon.demo.entities.Users;
import com.icreon.demo.helper.MyConnection;

@Service
public class DictionaryService {

	@Autowired
	UsersDao dao;

	@Autowired
	UserDetailsDao userdetails;

	public Users addUser(Users users) {

		dao = new UsersDao(MyConnection.getMyConnection());
		UsersDao.addUser(users);
		return users;

	}

	public Users getUsersByEmailandPassword(String email, String password) {
		dao = new UsersDao(MyConnection.getMyConnection());
		return UsersDao.getUserByEmailAndPassword(email, password);
	}

	public Users getUserByEmailAndPasswordAndUserType(String email, String password, String user_type) {
		dao = new UsersDao(MyConnection.getMyConnection());
		return dao.getUserByEmailAndPasswordAndUserType(email, password, user_type);
	}

	public List<Users> getRequests() {
		List<Users> requests = userdetails.getRequests();
		return requests;

	}

	public List<Users> getAllUsers() {
		List<Users> all = userdetails.getAll();
		return all;

	}

	public void deleteUser(String id, String username) {
		userdetails.delete(id, username);
	}

	public void deleteUserByAdmin(String role, String username) {
		userdetails.UserDeleteByAdmin(role, username);

	}

	public void update(String username, String role) {
		userdetails.update(username, role);
	}

	public Users getUserByEmailAndPassword1(String email) {
		dao = new UsersDao(MyConnection.getMyConnection());
		return UsersDao.getUserByEmailAndPassword1(email);
	}
}
