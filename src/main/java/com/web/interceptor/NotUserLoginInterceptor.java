package com.web.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.user.service.IUserService;

@Component
public class NotUserLoginInterceptor extends HandlerInterceptorAdapter implements SessionNames{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Object obj = session.getAttribute(LOGIN);
			if(obj != null) {
				PrintWriter printwriter = response.getWriter();
				printwriter.print("<script>"
						+"location.href='/'"
						+ "</script>");
				printwriter.flush();
				printwriter.close();
				return false;
			}
		}
		return true;
	}
}