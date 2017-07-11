package com.cts.spring.boot.DatabaseTestTwo.service;

import java.util.List;

import com.cts.spring.boot.DatabaseTestTwo.model.UserAccount;

public interface UserAccountService {
	void create(UserAccount userAccount);
	
	void update(UserAccount userAccount);
	
	void delete(String userName);
    
    String fetchUserPassByUserName(String userName);
    
    UserAccount fetchUserAccountByUserName(String userName);
    
    List<UserAccount> getAllUserAccounts();
}
