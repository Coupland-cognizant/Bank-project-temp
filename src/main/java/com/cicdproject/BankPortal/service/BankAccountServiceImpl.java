package com.cicdproject.BankPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cicdproject.BankPortal.dao.BankAccountDAOInterface;
import com.cicdproject.BankPortal.dao.BankAccountDAOImpl;
import com.cicdproject.BankPortal.model.BankAccountModel;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountServiceInterface {
    @Autowired
	private BankAccountDAOImpl bankAccountDAOInterface;
	 
	@Override
	public void create(BankAccountModel newAccount) {
		bankAccountDAOInterface.create(newAccount);
	}

	@Override
	public void update(BankAccountModel existingAccount) {
		bankAccountDAOInterface.update(existingAccount);
	}

	@Override
	public void delete(String email) {
		bankAccountDAOInterface.delete(email);
	}

	@Override
	public BankAccountModel fetchAccountByEMail(String email) {
		BankAccountModel accountReference = bankAccountDAOInterface.getAccountByEMail(email);
		if (accountReference != null) {
			return accountReference;
		}
		
		return null;
	}

	
}
