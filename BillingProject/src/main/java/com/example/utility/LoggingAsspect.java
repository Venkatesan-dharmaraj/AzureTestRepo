package com.example.utility;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAsspect {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "execution(* com.example.controller.*.*(..))", throwing = "exception")
	public void logControllerException(Exception exception) {
		log(exception);
	}
	
	@AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) {
		log(exception);
	}
	
	@AfterThrowing(pointcut = "execution(* com.example.repository.*.*(..))", throwing = "exception")
	public void logRepositoryException(Exception exception) {
		log(exception);
	}
	
	@Around(value = "execution(* com.example.controller.*.*(..))"  )
	public Object logAllParameter(ProceedingJoinPoint  joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
	    Object result = joinPoint.proceed();
	    logger.info(	      
	      MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName(),
	      joinPoint.getArgs(),
	      result,
	      System.currentTimeMillis() - start
	    );
	    return result;
	}
	
	private void log(Exception exception) {
		logger.error(exception.getMessage(), exception);
	}
	

}
