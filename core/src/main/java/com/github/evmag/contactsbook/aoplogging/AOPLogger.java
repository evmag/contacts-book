package com.github.evmag.contactsbook.aoplogging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLogger {
    // === Constants ===
    private static final Logger log = LoggerFactory.getLogger(AOPLogger.class);

    // === Pointcut declarations ===
    // Pointcut for all methods in all classes in service package
    @Pointcut("execution(* com.github.evmag.contactsbook.service.*.*(..))")
    private void servicePackage() {};

    // === Advices ===
    // Method called logging advice
    @Before("servicePackage()")
    public void logMethodCall(JoinPoint joinPoint) {
        log.trace("Method called: " + joinPoint.getSignature().toShortString());

        Object[] args = joinPoint.getArgs();
        for (int i=0; i < args.length; i++) {
            log.trace("Argument #" + i + " : " + args[i].toString());
        }
    }
}
