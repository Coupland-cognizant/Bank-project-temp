package com.cicdproject.BankPortal.dao;

import com.cicdproject.BankPortal.model.BankAccountModel;

public interface BankAccountDAOInterface {
	void create(BankAccountModel bankAccount);

    void update(BankAccountModel bankAccount);

    BankAccountModel getAccountByEMail(String email);

    void delete(String email);
}
