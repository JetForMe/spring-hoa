package com.latencyzero.hoa;



import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



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
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, consumes = "application/json")
	User
	getUser(@PathVariable("userId") String inUserIdOrLogin)
	{
		sLogger.info("getUser");
		return validateUser(inUserIdOrLogin);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	ModelAndView
	getUserHTML(@PathVariable("userId") String inUserIdOrLogin)
	{
		sLogger.info("getUserHTML");
		User user = validateUser(inUserIdOrLogin);
		sLogger.info("User: " + user.getLogin());
		ModelAndView mav = new ModelAndView("users/user");
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?>
	createUser(@RequestBody User inNewUser)
	{
		Optional<User> existingUser = mUserRepository.findByLogin(inNewUser.getLogin());
		if (existingUser.isPresent())
		{
			throw new UserExistsException(inNewUser.getLogin());
		}
		
		User result = mUserRepository.save(inNewUser);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
									.fromCurrentRequest().path("/{id}")
									.buildAndExpand(result.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	/**
		Throws a UserNotFoundException if the user ID or login doesn't
		exist.
	*/
	
	User
	validateUser(String inUserIdOrLogin)
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
	
	@ResponseBody
	@ExceptionHandler(UserExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	VndErrors
	userExistsxceptionHandler(UserExistsException inException)
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

@SuppressWarnings("serial")
class
UserExistsException
	extends
		RuntimeException
{
	public
	UserExistsException(String inLogin)
	{
		super("A user with login [" + inLogin + "] already exists.");
	}
}
