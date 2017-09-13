package com.Collaborate.Config;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class WebResolver 
{
	@Bean
	public InternalReaourceViewResolver getViewResolver()
	{
	InternalResourceViewResolver internalResourceViewResolver=new InternalResourcesViewResolver();
	internalResourcesourceViewResolver.setPrefix("/WEB-INF/");
	internalResourceViewResolver.setSuffix(".jsp");
	System.out.println("Resolver Created");
	return internalResourceViewResolver;
	}
}



