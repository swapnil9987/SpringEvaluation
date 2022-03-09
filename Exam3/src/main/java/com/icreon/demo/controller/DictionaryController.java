package com.icreon.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icreon.demo.dao.UsersDao;
import com.icreon.demo.entities.Message;
import com.icreon.demo.entities.Users;
import com.icreon.demo.helper.MyConnection;
import com.icreon.demo.service.DictionaryService;

@Controller
public class DictionaryController {
	@Autowired
	Users users;

	

	/*
	 * @Autowired UsersDao dao;
	 */

	@Autowired
	DictionaryService repo;

	@PostMapping("/Register")
	public String addUser(String user_name, String user_email, String user_password, String user_type) {

		users = new Users(user_name, user_email, user_password, user_type);

		// dao = new UsersDao(MyConnection.getMyConnection());

		if (user_type.equals("Admin")) {
			users.setUserType("Null");
			repo.addUser(users);
			return "login_page.jsp";
		} else {
			repo.addUser(users);
			return "login_page.jsp";
		}

	}

	@PostMapping("/Login")
	public String logIn(String email, String password, HttpServletRequest request, HttpServletResponse response) {

		users = repo.getUsersByEmailandPassword(email, password);

		if (users == null) {
			// login.................
//            error///
//            out.println("Invalid Details..try again");
			Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger");
			HttpSession s = request.getSession();
			s.setAttribute("msg", msg);

			return "login_page.jsp";
		} else {
			// ......
//            login success
			HttpSession s = request.getSession();
			s.setAttribute("currentUser", users);
			return "profile.jsp";

		}

	}

	@GetMapping("/Logout")
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession s = request.getSession();

		s.removeAttribute("currentUser");

		Message m = new Message("Logout Successfully", "success", "alert-success");

		s.setAttribute("msg", m);

		return "login_page.jsp";
	}

	@PostMapping("/AdminLogin")
	public String adminLogin(String email, String password, String user_type, HttpServletRequest request,
			HttpServletResponse response) {

		users = repo.getUserByEmailAndPasswordAndUserType(email, password, user_type);

		if (users == null) {
			// login.................
//            error///
//            out.println("Invalid Details..try again");
			Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger");
			HttpSession s = request.getSession();
			s.setAttribute("msg", msg);

			return "admin_login.jsp";

		} else if (user_type.equals("super_admin")) {
			// ......
//          login success
			HttpSession s = request.getSession();
			s.setAttribute("currentUser", users);
			return "admin_profile.jsp";

		} else if (user_type.equals("admin")) {
			System.out.println("welcome to admin page...");
			HttpSession s = request.getSession();
			s.setAttribute("currentUser", users);
			return "admin1_profile.jsp";

		}
		// {
//			Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger");
//			return "admin_login.jsp";
//		}
		return null;
	}

	@GetMapping("/AdminLogout")
	public String adminLogOut(HttpServletRequest request, HttpServletResponse response) {

		HttpSession s = request.getSession();

		s.removeAttribute("currentUser");

		Message m = new Message("Logout Successfully", "success", "alert-success");

		s.setAttribute("msg", m);

		return "admin_login.jsp";

	}

	@GetMapping("/UserRequest")
	public ModelAndView userRequests() {

		ModelAndView model = new ModelAndView();
		model.addObject("data", repo.getRequests());
		model.setViewName("/role_details.jsp");

		return model;

	}

	@GetMapping("/UserDisplay")
	public ModelAndView userDisplay() {

		ModelAndView model = new ModelAndView();
		model.addObject("data", repo.getAllUsers());
		model.setViewName("/user_details.jsp");

		return model;
	}

	@GetMapping("/UserDisplayAdmin")
	public ModelAndView userDisplayAdmin() {

		ModelAndView model = new ModelAndView();
		model.addObject("data", repo.getAllUsers());
		model.setViewName("/user_detailsAdmin.jsp");

		return model;
	}

	@GetMapping("/UserDelete")
	public ModelAndView userDelete(String id, String username) {

		repo.deleteUser(id, username);
		ModelAndView model = new ModelAndView();
		model.addObject("data", repo.getAllUsers());
		model.setViewName("/user_details.jsp");
		return model;
	}

	@GetMapping("/UserDeleteByAdmin")
	public ModelAndView userDeleteByAdmin(String role, String username) {

		repo.deleteUserByAdmin(role, username);
		ModelAndView model = new ModelAndView();
		model.addObject("data", repo.getAllUsers());
		model.setViewName("/user_detailsAdmin.jsp");
		return model;
	}

	@GetMapping("/RoleUpdate")
	public ModelAndView userUpdate(String username, String role) {

		repo.update(username, role);
		ModelAndView model = new ModelAndView();
		model.addObject("data", repo.getRequests());
		model.setViewName("/role_details.jsp");
		return model;

	}

	
}
