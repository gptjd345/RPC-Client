package com.example.toyProject.controller.member;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomHttpRequestServlet extends HttpServletRequestWrapper 
												implements Serializable  {
	
	
	private static final long serialVersionUID = 2L;

	
	public CustomHttpRequestServlet(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}


	

}