package com.latencyzero.hoa;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public
class
User
{
	public
	User(String inLogin, String inClearPassword)
	{
		mLogin = inLogin;
		mEncryptedPassword = inClearPassword;		//	TODO
	}
	
	public	Long			getId()						{ return mId; }
	public	String			getLogin()					{ return mLogin; }
	
	@Id
	@GeneratedValue
	private Long					mId;

	@JsonIgnore
	public String					mEncryptedPassword;
	public String					mLogin;
}
