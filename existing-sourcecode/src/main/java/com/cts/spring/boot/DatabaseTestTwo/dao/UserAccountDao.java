package com.cts.spring.boot.DatabaseTestTwo.dao;

import java.util.List;

import com.cts.spring.boot.DatabaseTestTwo.model.UserAccount;

public interface UserAccountDao {
	void create(UserAccount userAccount);

    void update(UserAccount userAccount);

    UserAccount getUserAccountByUserName(String userName);

    void delete(String userName);
    
    List<UserAccount> getAllUserAccounts();
}
