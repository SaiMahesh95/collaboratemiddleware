package com.Collaborate.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class WebResolver 
{
	@Bean
	public InternalResourceViewResolver getViewResolver()
	{
	InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
	internalResourceViewResolver.setPrefix("/WEB-INF/");
	internalResourceViewResolver.setSuffix(".jsp");
	System.out.println("Resolver Created");
	return internalResourceViewResolver;
	}
}



