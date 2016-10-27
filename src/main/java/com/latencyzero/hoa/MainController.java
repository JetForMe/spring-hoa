package com.latencyzero.hoa;


import java.util.Map;
import java.util.logging.Logger;


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
		sLogger.info("INDEX");
		
		java.util.Properties props = System.getProperties();
		for (Map.Entry<Object, Object> e : props.entrySet())
		{
			String key = (String) e.getKey();
			String value = (String) e.getValue();
			sLogger.info("Prop: " + key + ": " + value);
		}
		
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("version", "0.1");
		return mav;
	}
	
	//	TODO: Add root REST call that returns some fancy HATEOAS
	//	thing documenting the API.
	
	private static final Logger		sLogger		=	Logger.getLogger(MainController.class.getName());
}
