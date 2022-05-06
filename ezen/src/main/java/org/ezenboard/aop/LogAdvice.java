package org.ezenboard.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	/*
	 * @Before("execution(* org.ezenboard.service.SampleService*.*(..))") public
	 * void logBefore() { log.info("================"); }
	 * 
	 * @Before("execution(* org.ezenboard.service.SampleService*.doAdd(String, String))"
	 * + "&& args(str1, str2)") public void logBeforeWithParam(String str1, String
	 * str2) { log.info("================"); log.info(str1); log.info(str2); }
	 * 
	 * @AfterThrowing(pointcut =
	 * "execution(* org.ezenboard.service.SampleService*.*(..))", throwing =
	 * "exception") public void afterException(Exception exception) {
	 * log.info("exception발생 : " + exception); }
	 */
	
	@Around("execution(* org.ezenboard.service.BoardService*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {

		long start = System.currentTimeMillis();

		log.info("Target: " + pjp.getTarget());
		log.info("Param: " + Arrays.toString(pjp.getArgs()));

		// invoke method
		Object result = null;

		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		log.info("TIME: " + (end - start));

		return result;
	}
	
}
