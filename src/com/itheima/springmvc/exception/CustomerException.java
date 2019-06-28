package com.itheima.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomerException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		// TODO Auto-generated method stub
		ModelAndView view = new ModelAndView();
		
		//预期异常
		if(e instanceof MessageException) {
			MessageException me = (MessageException) e;
			view.addObject("error", me.getMsg());
		}else {
			view.addObject("error", "未知异常"); 
		}
				
		view.setViewName("error");
		return view;
	}

}
