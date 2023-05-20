package com.project.utils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {
	public static String getUriPath(ServletRequest request) {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		return httpReq.getServletPath();
	}
}
