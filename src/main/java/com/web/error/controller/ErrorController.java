package com.web.error.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping(value = "exception", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView exception(HttpServletRequest request) {
		ModelAndView errorPage = new ModelAndView("errors/exceptionError");
		pageErrorLog(request);
		errorPage.addObject("title", "500");
		errorPage.addObject("msg", "databases error");
		errorPage.addObject("ex", "Table doesn't exist");
		return errorPage;
	}
	
	@RequestMapping(value = "errors", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView renderGetErrorPage(HttpServletRequest request) {
		pageErrorLog(request);
		int httpErrorCode = getErrorCode(request);
		ModelAndView errorPage = new ModelAndView("errors/error");
		String title = "";
		String msg = "";
		String ex = "";
		
		switch (httpErrorCode) {
			case 400: {
				title = "400";
				msg = "400";
				ex = "400";
				break;
			}
			case 404: {
				title = "404";
				msg = "404 요청하신 페이지를 찾을 수 없습니다.";
				ex = "주소가 바뀌었거나 잘못 입력된 주소입니다.";
				break;
			}
			case 405: {
				title = "405";
				msg = "405 요청하신 메서드가 허용되지 않습니다.";
				ex = "요청에 의해 식별 되는 리소스에 대 한 요청 라인에 지정 된 메서드가 허용 되지 않습니다. 적절 한 MIME 형식을 요청 하는 리소스에 대 한 설정 있는지 확인 하십시오.";
				break;
			}
			case 500: {
				title = "500";
				msg = "databases error";
				ex = "Table doesn't exist";
				break;
			}
		}
		errorPage.addObject("title", title);
		errorPage.addObject("msg", msg);
		errorPage.addObject("ex", ex);
		
		
		return errorPage;
	}

	// 에러코드 가져오는 메서드
	private int getErrorCode(HttpServletRequest request) {
		return (Integer) request.getAttribute("javax.servlet.error.status_code");
	}
	
	private void pageErrorLog(HttpServletRequest request) {
		int code = (int) request.getAttribute("javax.servlet.error.status_code");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		Object type = request.getAttribute("javax.servlet.error.exception_type");
		Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
		String name = (String) request.getAttribute("javax.servlet.error.servlet_name");
		
		logger.info("error code [" + code + "]");
		logger.info("발생된 오류 객체의 타입 정보를 가지고 있는 Class형 객체 message [" + message + "]");
		logger.info("발생된 오류의 메시지 정보 type [" + type + "]");
		logger.info("발생된 오류 객체 exception [" + exception + "]");
		logger.info("오류가 발생된 페이지의 URI정보 uri [" + uri + "]");
		logger.info("에러가 난 서블릿 명 출력 [" + name + "]");
	}
}
