package com.project.filters;

import java.io.IOException;

import com.project.utils.Utils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class MedicineFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String email = request.getParameter("email");
		System.out.println("##################Medicine Filter##################");
		RequestDispatcher dispatcher;
		if(email == null) {
			dispatcher = request.getRequestDispatcher("/api/admin/error");
			dispatcher.forward(request, response);
		}
		else {
			dispatcher = request.getRequestDispatcher(Utils.getUriPath(request));
			dispatcher.forward(request, response);
		}
	}
	

}
