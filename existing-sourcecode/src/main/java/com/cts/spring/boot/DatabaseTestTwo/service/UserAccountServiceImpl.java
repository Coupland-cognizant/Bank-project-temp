package com.cts.spring.boot.DatabaseTestTwo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.spring.boot.DatabaseTestTwo.dao.UserAccountDao;
import com.cts.spring.boot.DatabaseTestTwo.model.UserAccount;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountDao userAccountDao;
	
	@Override
	public void create(UserAccount userAccount) {
		userAccountDao.create(userAccount);
	}

	@Override
	public String fetchUserPassByUserName(String userName) {
		UserAccount accountReference = userAccountDao.getUserAccountByUserName(userName);
		if (accountReference != null) {
			return accountReference.getUserPass();
		}
		
		return "No Account with Username: " + userName;
	}

	@Override
	public UserAccount fetchUserAccountByUserName(String userName) {
		return userAccountDao.getUserAccountByUserName(userName);
	}

	@Override
	public void update(UserAccount userAccount) {
		userAccountDao.update(userAccount);
	}

	@Override
	public List<UserAccount> getAllUserAccounts() {		
		return userAccountDao.getAllUserAccounts();
	}

	@Override
	public void delete(String userName) {
		userAccountDao.delete(userName);
	}

}
