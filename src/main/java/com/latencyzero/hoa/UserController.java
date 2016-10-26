package com.latencyzero.hoa;


import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



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
	getUser(@PathVariable("userId") String inUserIdOrLogin)
	{
		Optional<User> user = null;
		
		//	Try it as a numerical user ID…
		
		try
		{
			Long userID = Long.valueOf(inUserIdOrLogin);
			user = mUserRepository.findById(userID);
		}
		
		catch (NumberFormatException ex)
		{
			//	It's not a numerical ID, so assume it's a login…
			
			user = mUserRepository.findByLogin(inUserIdOrLogin);
		}
		
		return user.get();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	User
	createUser(@PathVariable("userId") String inUserIdOrLogin)
	{
		Optional<User> user = null;
		
		//	Try it as a numerical user ID…
		
		try
		{
			Long userID = Long.valueOf(inUserIdOrLogin);
			user = mUserRepository.findById(userID);
		}
		
		catch (NumberFormatException ex)
		{
			//	It's not a numerical ID, so assume it's a login…
			
			user = mUserRepository.findByLogin(inUserIdOrLogin);
		}
		
		return user.orElseThrow(() -> new UserNotFoundException(inUserIdOrLogin));
	}
	
	
	private	final	UserRepository			mUserRepository;
	
	private static final Logger		sLogger		=	Logger.getLogger(UserController.class.getName());
}

@ControllerAdvice
class
UserControllerAdvice
{
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors
	userNotFoundExceptionHandler(UserNotFoundException inException)
	{
		return new VndErrors("error", inException.getMessage());
	}
}

@SuppressWarnings("serial")
class
UserNotFoundException
	extends
		RuntimeException
{
	public
	UserNotFoundException(String inUserId)
	{
		super("No user with ID [" + inUserId + "].");
	}
}
