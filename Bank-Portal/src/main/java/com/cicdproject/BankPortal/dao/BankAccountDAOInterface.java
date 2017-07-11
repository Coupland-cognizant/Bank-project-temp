package com.cicdproject.BankPortal.dao;

import com.cicdproject.BankPortal.model.BankAccountInterface;

public interface BankAccountDAOInterface {
	void create(BankAccountInterface bankAccount);

    void update(BankAccountInterface bankAccount);

    BankAccountInterface getUserAccountByUserName(String email);

    void delete(String email);
}
