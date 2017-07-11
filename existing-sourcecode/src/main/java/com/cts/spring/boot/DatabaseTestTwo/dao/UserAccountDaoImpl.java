package com.cts.spring.boot.DatabaseTestTwo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cts.spring.boot.DatabaseTestTwo.model.UserAccount;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public void create(UserAccount userAccount) {
		entityManager.persist(userAccount);
	}

	@Override
	public void update(UserAccount userAccount) {
		entityManager.merge(userAccount);
	}

	@Override
	public UserAccount getUserAccountByUserName(String userName) {
		return entityManager.find(UserAccount.class, userName);
	}

	@Override
	public void delete(String userName) {
		UserAccount accountReference = getUserAccountByUserName(userName);
        if (accountReference != null) {
            entityManager.remove(accountReference);
        } else {
        	// log that the requested ID did not exist
        	System.out.printf("Delete Error: User Acount %s does not exist.\n", userName);
        }
	}
	
	@Override
	public List<UserAccount> getAllUserAccounts() {
		// This query is the equivelent of "SELECT * FROM USER_ACCOUNTS"
		// no idea why we use "from <entity type>", but it works
		return entityManager.createQuery("from UserAccount", UserAccount.class).getResultList();
	}
}
