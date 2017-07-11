package com.cicdproject.BankPortal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cicdproject.BankPortal.model.BankAccountModel;
import com.cicdproject.BankPortal.service.BankAccountServiceImpl;
import com.cicdproject.BankPortal.service.BankAccountServiceInterface;
import com.cicdproject.BankPortal.utities.HTMLUtils;

@RestController
@EnableAutoConfiguration
public class HomePageController {
	
	@Autowired
	private BankAccountServiceImpl bankAccountService;
	
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
		BankAccountModel bankModel = bankAccountService.fetchAccountByEMail(loginEMail);
		if(bankModel != null && bankModel.getEmail().length() > 0 && bankModel.getEmail().length() <= 254
				&& bankModel.getPassword().length() > 0 && bankModel.getPassword().length() <= 60
				&& bankModel.getPassword().equals(loginPass)) 
		{
			return HTMLUtils.getHtmlFromFile("src/main/resources/static/success.html");
		}
		else
		{
			return HTMLUtils.getHtmlFromFile("src/main/resources/static/failure.html");
		}
	}
	
	@RequestMapping(value = {"/signup"},
			params = {"signupEMail", "signupPass"},
    		method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML_VALUE)
    public String signupPage(@RequestParam("signupEMail") String signupEMail, @RequestParam("signupPass") String signupPass) {
		System.out.println("Signup - Email: " + signupEMail + ", Password: " + signupPass);
		
		// validate data
		if (signupEMail.length() > 0 && signupEMail.length() <= 254
				&& signupPass.length() > 0 && signupPass.length() <= 60) 
		{
			BankAccountModel bankModel = bankAccountService.fetchAccountByEMail(signupEMail);
			if(bankModel != null) 
			{
				bankModel.setEmail(signupEMail);
				bankModel.setPassword(signupPass);
				bankAccountService.update(bankModel);
			}
			else
			{
				BankAccountModel newBankModel = new BankAccountModel(signupEMail, signupPass);
				bankAccountService.create(newBankModel);
			}
			// valid
			return HTMLUtils.getHtmlFromFile("src/main/resources/static/success.html");
		} 
		else 
		{
			// invalid
			return HTMLUtils.getHtmlFromFile("src/main/resources/static/failure.html");
		}
	}
}
