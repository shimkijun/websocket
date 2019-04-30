package com.web.utill;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebListener
public class SessionUser implements HttpSessionListener,HttpSessionAttributeListener{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionUser.class);
	private static int totalActiveSessions;
	
	@Override
	public void sessionCreated(HttpSessionEvent e) {
		totalActiveSessions++;
		logger.info("브라우저 접속자 수 ====== {}",totalActiveSessions);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		totalActiveSessions--;
		logger.info("브라우저 접속자 수 ====== {}",totalActiveSessions);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		logger.info("attributeAdded ====== {}",event.getName());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		logger.info("attributeRemoved ====== {}",event.getName());
	}
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}


}
