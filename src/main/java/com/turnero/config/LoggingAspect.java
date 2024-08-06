package com.turnero.config;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggingAspect {


    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


	 /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.turnero.service..*)" +
        " || within(com.turnero.manager..*)" +
        " || within(com.turnero.controller..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
        public void springBeanPointcut() {
            // Method is empty as this is just a Pointcut, the implementations are in the advices.
        }

	 @Around("applicationPackagePointcut() && springBeanPointcut()")
	 public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	        if (logger.isDebugEnabled()) {
	        	logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
	        }
	        try {
	            Object result = joinPoint.proceed();
	            if (logger.isDebugEnabled()) {
	            	logger.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
	                    joinPoint.getSignature().getName(), result);
	            }
	            return result;
	        } catch (IllegalArgumentException e) {
	        	logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
	                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
	            throw e;
	        }
	    }
}
