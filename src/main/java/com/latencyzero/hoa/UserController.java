package com.latencyzero.hoa;


import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping("/users")
class
UserController
{
	@Autowired
	UserController(UserRepository inUserRepository)
	{
		mUserRepository = inUserRepository;
	}
	
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	User
	getUser(@PathVariable("userId") String inUserId)
	{
//		this.validateUser(userId);
		return mUserRepository.findByLogin(inUserId).get();
	}
	
	private	final	UserRepository			mUserRepository;
	
	private static final Logger		sLogger		=	Logger.getLogger(UserController.class.getName());
}
