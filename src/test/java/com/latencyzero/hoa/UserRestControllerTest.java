package com.latencyzero.hoa;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;









@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public
class
UserRestControllerTest
{
	@Before
	public
	void
	setup()
		throws
			Exception
	{
		mMockMVC = webAppContextSetup(webApplicationContext).build();
	}

    @Test
    public
	void
	userNotFound()
		throws
			Exception
	{
		mMockMVC
			.perform(post("/users/george")
			//.content(this.json(new Bookmark()))
			.contentType(sContentType))
			.andExpect(status().isNotFound());
    }



	private MockMvc								mMockMVC;
    private static final	MediaType			sContentType			=	new MediaType(MediaType.APPLICATION_JSON.getType(),
																							MediaType.APPLICATION_JSON.getSubtype(),
																							Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext				webApplicationContext;
}
