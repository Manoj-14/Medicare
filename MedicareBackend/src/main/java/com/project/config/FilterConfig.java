package com.project.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.filters.LoginFilter;
import com.project.filters.MedicineFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<LoginFilter> adminAuth(){
	    FilterRegistrationBean<LoginFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	    registrationBean.setFilter(new LoginFilter());
	    registrationBean.setOrder(1);
	    registrationBean.addUrlPatterns("/api/admin/changepassword");
	    return registrationBean;    
	}
	
	@Bean
	public FilterRegistrationBean<MedicineFilter> medicineAuth(){
	    FilterRegistrationBean<MedicineFilter> medicineAuthBean 
	      = new FilterRegistrationBean<>();
	    medicineAuthBean.setFilter(new MedicineFilter());
	    medicineAuthBean.setOrder(2);
	    medicineAuthBean.addUrlPatterns("/api/medicin/*");
	    return medicineAuthBean;
	}
}
