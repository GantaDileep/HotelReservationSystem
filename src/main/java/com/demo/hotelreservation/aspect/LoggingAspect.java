package com.demo.hotelreservation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.demo.hotelreservation.controller..*(..))")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint)
            throws Throwable {

        String methodName = joinPoint.getSignature().getName();

        log.info("Method {} started", methodName);

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long executionTime =
                    System.currentTimeMillis() - startTime;

            log.info("Method {} completed in {} ms",
                    methodName, executionTime);

            return result;

        } catch (Throwable exception) {

            log.error("Exception occurred in method {}",
                    methodName, exception);

            throw exception;
        }
    }
}
