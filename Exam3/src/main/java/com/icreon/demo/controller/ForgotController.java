package com.icreon.demo.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.icreon.demo.entities.Users;
import com.icreon.demo.service.DictionaryService;
import com.icreon.demo.service.EmailService;

@Controller
public class ForgotController {
	@Autowired
	DictionaryService repo;
	
	@Autowired
	EmailService emailService;

	@GetMapping("/forgotPassword")
	public String openEmailForm() {

		return "forgotPassword.jsp";
	}

	@GetMapping("/SendOtp")
	public String SendOtp(@RequestParam("email") String email, HttpSession session) {

		// System.out.println("email is : "+email);

		// generating 4 digit otp

		Random random = new Random();

		int otp = random.nextInt(1000, 9999);
		System.out.println("OTP : " + otp);

		String subject = "OTP from Online Dictionary";
		String message = "OTP is : " + otp;
		String to = email;

		boolean flag = this.emailService.sendEmail(subject, message, to);

		if (flag) {

			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			return "verifyOtp.jsp";
		} else {

			return "forgotPassword.jsp";
		}

	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") Integer otp, HttpSession session) {
		
		Integer myOtp = (int) session.getAttribute("myotp");
		String email = (String)session.getAttribute("email");
		
		if (myOtp.equals(otp)) {
			System.out.println("in true..");
			return "changePasswordForm.jsp";
			
		}else {
			System.out.println("in false...");
			//session.setAttribute("message", "You have entered wrong OTP");
			return "verifyOtp.jsp";
		}
	}
	
	@PostMapping("/changePassword")
	public String changePass(@RequestParam("newPassword") String newPassword, HttpSession session) {
		
		String email = (String)session.getAttribute("email");
		
		Users users = this.repo.getUserByEmailAndPassword1(email);
		users.setPassword(newPassword);
		this.repo.addUser(users);
		return "login_page.jsp";
	}

}
