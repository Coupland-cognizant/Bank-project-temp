package com.cicdproject.BankPortal.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cicdproject.BankPortal.utities.HTMLUtils;

@RestController
@EnableAutoConfiguration
public class HomePageController {
	
	@RequestMapping(value = {"/"}, produces = MediaType.TEXT_HTML_VALUE)
    public String homePage() {
		return HTMLUtils.getHtmlFromFile("src/main/resources/static/home.html");
	}
	
	@RequestMapping(value = {"/login"},
			params = {"loginEMail", "loginPass"},
    		method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML_VALUE)
    public String loginPage(@RequestParam("loginEMail") String loginEMail, @RequestParam("loginPass") String loginPass) {
		System.out.println("Signup - Email: " + loginEMail + ", Password: " + loginPass);
		return HTMLUtils.getHtmlFromFile("src/main/resources/static/under-construction.html");
	}
	
	@RequestMapping(value = {"/signup"},
			params = {"signupEMail", "signupPass"},
    		method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML_VALUE)
    public String signupPage(@RequestParam("signupEMail") String signupEMail, @RequestParam("signupPass") String signupPass) {
		System.out.println("Signup - Email: " + signupEMail + ", Password: " + signupPass);
		return HTMLUtils.getHtmlFromFile("src/main/resources/static/under-construction.html");
	}
}
