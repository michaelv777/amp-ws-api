package com.amp.managed.ws.controller;

import java.util.Set;

//@ApplicationPath("commerce")
public class ApplicationConfig //extends Application 
{

	public Set<Class<?>> getClasses() {
        return getRestClasses();
    }
    
	//Auto-generated from RESTful web service wizard
    private Set<Class<?>> getRestClasses() {
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		
		resources.add(com.amp.managed.ws.controller.AmazonService.class);
		return resources;    
    }
}