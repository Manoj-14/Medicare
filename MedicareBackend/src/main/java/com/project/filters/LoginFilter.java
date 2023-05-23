package com.project.filters;

import java.io.IOException;
import java.util.Enumeration;

import com.project.utils.Utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;


public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Enumeration<String> attributeNames = request.getAttributeNames();
		  while (attributeNames.hasMoreElements()) {
		    String attributeName = attributeNames.nextElement();
		    Object attributeValue = request.getAttribute(attributeName);
		    System.out.println(attributeName+":"+attributeValue);
		  }
		System.out.println("####################### Filter #####################");
		RequestDispatcher dispatcher;
		if(email == null || password == null) {
			dispatcher = request.getRequestDispatcher("/api/admin/error");
			dispatcher.forward(request, response);
		}
		else {
			dispatcher = request.getRequestDispatcher(Utils.getUriPath(request));
			dispatcher.forward(request, response);
		}
	}
	
}
