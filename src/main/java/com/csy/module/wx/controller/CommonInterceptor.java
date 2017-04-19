package com.csy.module.wx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class CommonInterceptor implements HandlerInterceptor {
	  //拦截前处理  
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {  
	    String path = request.getParameter("param");
	    String path1 = request.getRequestURI();
	    if(null != path && path1.contains("lose")){//说明是登录时候调用的
	    	return true;
	    }
	    response.sendRedirect(request.getContextPath() + "/login");  
	    return false;  
	  }   
	  //拦截后处理  
	  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception { }  
	  //全部完成后处理  
	  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception { }  

}
