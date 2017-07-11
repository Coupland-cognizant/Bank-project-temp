package com.cicdproject.BankPortal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cicdproject.BankPortal.model.BankAccountModel;

@Repository
public class BankAccountDAOImpl implements BankAccountDAOInterface {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(BankAccountModel bankAccount) {
		entityManager.persist(bankAccount);
	}

	@Override
	public void update(BankAccountModel bankAccount) {
		entityManager.merge(bankAccount);
	}

	@Override
	public BankAccountModel getAccountByEMail(String email) {
		
		return entityManager.find(BankAccountModel.class, email);
	}

	@Override
	public void delete(String email) {
		BankAccountModel bankReference=getAccountByEMail(email);
		if (bankReference != null) {
            entityManager.remove(bankReference);
        } else {
        	// log that the requested ID did not exist
        	System.out.printf("Delete Error: User Acount %s does not exist.\n", email);
        }
		
	}
	
	
	

}
