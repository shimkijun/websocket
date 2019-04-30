package com.web.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LoggingAspect {
	@Around("execution(public * com.web..*(..))")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result;
		//타겟 메서드의 signature 정보
		String signatureString = joinPoint.getSignature().toShortString();
		System.out.println(signatureString + "시작");
		
		//타겟의 메서드가 호출되기 전의 시간
		long start =System.currentTimeMillis();
		try {
			//타겟의 메서드 호출
			result = joinPoint.proceed();
			return result;
		} finally {
			// 타겟의 메서드가 호출된 후의 시간
			long finish = System.currentTimeMillis();
			System.out.println(signatureString + "종료");
			System.out.println(signatureString + "실행 시간 : " + (finish - start) + " ms");
		}
	}
	@Before("execution(public * com.web..*(..))")
	public void before(JoinPoint joinPoint) {
		String signatureString = joinPoint.getSignature().getName();
		System.out.println("@Before [" + signatureString + "] 메서드 실행 전처리 수행");
		for(Object arg:joinPoint.getArgs()) {
			System.out.println("@Before [" + signatureString + "] 아규먼트" + arg);
		}
	}
	
	@AfterReturning(pointcut="execution(public * com.web.user.service.*.*(..))",returning="ret")
	public void afterReturning(JoinPoint joinPoint, Object ret) {
		String signatureString = joinPoint.getSignature().getName();
		System.out.println("@AfterReturning [" + signatureString + "] 메서드 실행 후처리 수행");
		System.out.println("@AfterReturning [" + signatureString + "] 리턴값 = "+ret);
	}
	
	@AfterThrowing(pointcut="execution(* *..IUserService*.*(..))",throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		String signatureString = joinPoint.getSignature().getName();
		System.out.println("@AfterThrowing [" + signatureString + "] 메서드 실행 중 예외 발생");
		System.out.println("@AfterThrowing [" + signatureString + "] 예외 = "+ex);
	}
	
	@After("execution(* *..*.*User(..))")
	public void afterFinally(JoinPoint joinPoint) {
		String signatureString = joinPoint.getSignature().getName();
		System.out.println("@After [" + signatureString + "] 메서드 실행 완료");
	}
}
