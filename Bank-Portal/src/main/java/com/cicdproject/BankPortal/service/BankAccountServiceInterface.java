package com.cicdproject.BankPortal.service;

import com.cicdproject.BankPortal.model.BankAccountModel;

public interface BankAccountServiceInterface {
	void create(BankAccountModel newAccount);
	
	void update(BankAccountModel existingAccount);
	
	void delete(String email);
    
	BankAccountModel fetchAccountByEMail(String email);
}
