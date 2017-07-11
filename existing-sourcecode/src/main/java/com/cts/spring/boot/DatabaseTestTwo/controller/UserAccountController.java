package com.cts.spring.boot.DatabaseTestTwo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.spring.boot.DatabaseTestTwo.model.UserAccount;
import com.cts.spring.boot.DatabaseTestTwo.service.UserAccountService;

@RestController
@EnableAutoConfiguration
public class UserAccountController {

	@Autowired
    private UserAccountService userAccountService;

    private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

    @RequestMapping(value = "/testAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addTestAccount() {
    	logger.info("Test Account Function Invoked");
    	Map<String, String> response = new HashMap<String, String>();
    	try {
    		UserAccount accountReference = userAccountService.fetchUserAccountByUserName("TestAccount");
    		if (accountReference != null) {
    			logger.info("Test Account Already Exists");
    			response.put("Details", accountReference.toString());
    		} else {
    			logger.info("Test Account Does Not Exist");
    			UserAccount testAccount = new UserAccount("TestAccount", "TestPass");
    			userAccountService.create(testAccount);
    			logger.info("Test Account Added");
    			response.put("Details", "Test Account Added");
    		}
    	} catch (Exception e) {
    		logger.error("Error occurred while trying to process api request", e);
            response.put("Status", "Fail");
    	}
    	return response;
    }
    
    @RequestMapping(value = {"/accountPage", "/"}, produces = MediaType.TEXT_HTML_VALUE)
    public String accountPage() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<html><head></head>");
    	sb.append("<body>");
    	sb.append("<p>Spring-Boot Database Test: Account Manager</p>");
    	sb.append("<a href=\"/listAccounts\">List all accounts in the Database</a><br/>");
    	sb.append("<a href=\"/addUser\">Add new account to the Database</a><br/>");
    	sb.append("<a href=\"/removeUser\">Remove an account from the Database</a>");
    	sb.append("</body>");
    	sb.append("</html>");
    	
    	return sb.toString();
    }
    
    @RequestMapping(value = "/listAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> listAllAccounts() {
    	Map<String, String> response = new HashMap<String, String>();
    	
    	List<UserAccount> accountList = userAccountService.getAllUserAccounts();
    	for (int i = 0; i < accountList.size(); i += 1) {
    		response.put("User Account " + i, accountList.get(i).toString());
    	}
    	
    	return response;
    }
    
    @RequestMapping(value = "/addUser",
    		method = RequestMethod.GET,
    		produces = MediaType.TEXT_HTML_VALUE)
    public String addAccountForm() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<html><head></head>");
    	sb.append("<body>");
    	sb.append("<p>Add Account Form:</p>");
    	sb.append("<form action=\"/addUser\" method=\"POST\">");
    	sb.append("Username:<br>");
    	sb.append("<input type=\"text\" name=\"userName\" value=\"\"><br/>");
    	sb.append("Password:<br>");
    	sb.append("<input type=\"password\" name=\"userPass\" value=\"\"><br/><br/>");
    	sb.append("<input type=\"submit\" value=\"Submit\">");
    	sb.append("</form>");
    	sb.append("</body>");
    	sb.append("</html>");
    	
    	return sb.toString();
    }
    
    @RequestMapping(
    		value = "/addUser",
    		params = {"userName", "userPass"},
    		method = RequestMethod.POST,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addAccount(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass) {
    	Map<String, String> response = new HashMap<String, String>();
    	
    	if (userName.length() == 0 || userPass.length() == 0) {
    		response.put("Error", "username or password field empty");
    		return response;
    	}
    	
    	UserAccount accountReference = userAccountService.fetchUserAccountByUserName(userName);
    	try {
	    	if (accountReference != null) {
	    		UserAccount updatedAccount = new UserAccount(userName, userPass);
	    		userAccountService.update(updatedAccount);
	    		response.put("Details", "updated account: " + updatedAccount.toString());
	    	} else {
	    		UserAccount newAccount = new UserAccount(userName, userPass);
    			userAccountService.create(newAccount);
    			response.put("Details", "Added account: " + newAccount.toString());
	    	}
    	} catch (Exception e) {
    		logger.error("Error occurred while trying to process api request", e);
            response.put("Status", "Fail");
    	}
    	
    	return response;
    }
    
    @RequestMapping(value = "/removeUser",
    		method = RequestMethod.GET,
    		produces = MediaType.TEXT_HTML_VALUE)
    public String removeAccountForm() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<html><head></head>");
    	sb.append("<body>");
    	sb.append("<p>Add Account Form:</p>");
    	sb.append("<form action=\"/removeUser\" method=\"POST\">");
    	sb.append("Username:<br>");
    	sb.append("<input type=\"text\" name=\"userName\" value=\"\"><br/><br/>");
    	sb.append("<input type=\"submit\" value=\"Submit\">");
    	sb.append("</form>");
    	sb.append("</body>");
    	sb.append("</html>");
    	
    	return sb.toString();
    }
    
    @RequestMapping(
    		value = "/removeUser",
    		params = {"userName"},
    		method = RequestMethod.POST,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> removeAccount(@RequestParam("userName") String userName) {
    	Map<String, String> response = new HashMap<String, String>();
    	
    	if (userName.length() == 0) {
    		response.put("Error", "username field is empty");
    		return response;
    	}
    	
    	UserAccount accountReference = userAccountService.fetchUserAccountByUserName(userName);
    	try {
	    	if (accountReference != null) {
	    		userAccountService.delete(userName);
	    		response.put("Details", "Deleted account: " + userName);
	    	} else {
    			response.put("Details", "Account name not found in database: " + userName);
	    	}
    	} catch (Exception e) {
    		logger.error("Error occurred while trying to process api request", e);
            response.put("Status", "Fail");
    	}
    	
    	return response;
    }
}
