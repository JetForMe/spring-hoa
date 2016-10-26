package com.latencyzero.hoa;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public
class
User
{
	/**
		Default constructor is needed by JPA.
	*/
	
	User()
	{
	}
	
	public
	User(String inLogin, String inClearPassword)
	{
		this.login = inLogin;
		mEncryptedPassword = inClearPassword;		//	TODO
	}
	
	public	Long			getId()						{ return mId; }
	public	String			getLogin()					{ return this.login; }
	
	@Id
	@GeneratedValue
	private Long					mId;

	@JsonIgnore
	public String					mEncryptedPassword;
//	@Column(name = "login")
	public String					login;
}
