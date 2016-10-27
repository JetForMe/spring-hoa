package com.latencyzero.hoa;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;







//@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public
class
Application
{
	@Bean
	CommandLineRunner
	init(UserRepository inUserRepository)
	{
		return (evt) -> Arrays.asList(
				"rick,amber,".split(","))
				.forEach(
					a -> {
						User account = inUserRepository.save(new User(a, "password"));
						});
	}
	
	public
	static
	void
	main(String[] inArgs)
	{
		sLogger.info("Launched!");
		java.util.Properties props = System.getProperties();
		for (Map.Entry<Object, Object> e : props.entrySet())
		{
			String key = (String) e.getKey();
			String value = (String) e.getValue();
			sLogger.info("Prop: " + key + ": " + value);
		}
		SpringApplication.run(Application.class, inArgs);
	}
	
	private static final Logger		sLogger		=	Logger.getLogger(Application.class.getName());
}
