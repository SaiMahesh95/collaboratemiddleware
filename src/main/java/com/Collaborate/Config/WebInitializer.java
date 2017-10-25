package com.Collaborate.Config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.collaborate.config.DBConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
	return new Class[]{DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
	return new Class[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
	return new String[]{"/"};

	}

	}