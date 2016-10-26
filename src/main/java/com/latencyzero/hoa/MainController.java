package com.latencyzero.hoa;




import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/")
class
MainController
{
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView
	index()
	{
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	//	TODO: Add root REST call that returns some fancy HATEOAS
	//	thing documenting the API.
}
