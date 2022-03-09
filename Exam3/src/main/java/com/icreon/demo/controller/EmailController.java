package com.icreon.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icreon.demo.entities.EmailRequest;
import com.icreon.demo.service.EmailService;




@RestController
public class EmailController {
	
	@Autowired
	EmailRequest email;
	
	@Autowired
	EmailService emailservice;
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		
		System.out.println(request);
		boolean sendEmail = this.emailservice.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		
		if (sendEmail) {
			return ResponseEntity.ok("Done...");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent...");
		}
		
		
			
		}
}
