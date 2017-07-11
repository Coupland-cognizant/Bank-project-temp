package com.cts.spring.boot.DatabaseTestTwo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
	// It's necessary to specify the full string type for the
	// User Account variables, otherwise accessing them and
	// setting them with the entity Manager may try to convert
	// them to numbers.
	
	@Id
	@Column(name="user_name")
	@NotNull
    @Size(max = 64)
    private java.lang.String userName;
	
	@Column(name="user_pass")
	@NotNull
    @Size(max = 64)
    private java.lang.String userPass;
	
	// constructors
	// It's necessary to have a default constructor as fetching entries
	// may throw an exception
	public UserAccount() {
		this.userName = "";
		this.userPass = "";
	}
	
	public UserAccount(String userName, String userPass) {
		this.userName = userName;
		this.userPass = userPass;
	}
	
	// Getters and Setters
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	@Override
	public String toString() {
		return "User Name: " + userName + ", Password: " + userPass;
	}
}
