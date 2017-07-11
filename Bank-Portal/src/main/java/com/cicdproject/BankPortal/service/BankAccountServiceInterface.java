package com.cicdproject.BankPortal.service;

import com.cicdproject.BankPortal.model.BankAccountInterface;

public interface BankAccountServiceInterface {
	void create(BankAccountInterface newAccount);
	
	void update(BankAccountInterface existingAccount);
	
	void delete(String email);
    
    String fetchAccountByEMail(String email);
}
