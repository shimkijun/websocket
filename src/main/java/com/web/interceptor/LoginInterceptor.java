package com.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.user.service.IUserService;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter implements SessionNames{

	@Autowired
	private IUserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		if(session != null) {
			Object obj = session.getAttribute(LOGIN);
			if(obj != null)
				return true;
		}
		PrintWriter printwriter = response.getWriter();
		printwriter.print("<script>"
				+"location.href='/user/login'"
				+ "</script>");
		printwriter.flush();
		printwriter.close();
		return false;
	}
	
}
