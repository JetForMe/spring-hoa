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
	
	public	Long			getId()						{ return this.id; }
	public	String			getLogin()					{ return this.login; }
	
	@Id
	@GeneratedValue
	private Long					id;

	@JsonIgnore
	public String					mEncryptedPassword;
	@Column(name = "mLogin")
	public String					login;
	
	@Column(name = "mUsername")
	public String					username;
	
	@Column(name = "mFirst")
	public String					first;
	
	@Column(name = "mNick")
	public String					nick;
	
	@Column(name = "mLast")
	public String					last;
}
