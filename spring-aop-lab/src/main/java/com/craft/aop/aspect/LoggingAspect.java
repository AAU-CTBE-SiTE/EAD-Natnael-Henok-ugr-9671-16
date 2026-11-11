package com.craft.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ASPECT = a class that holds cross-cutting code (here: logging).
 * ADVICE = each method below (WHAT to run and WHEN).
 * POINTCUT = the expression that says WHICH methods to apply it to.
 */
@Aspect
@Component
@Order(1)
public class LoggingAspect {

    // A reusable pointcut: every method of StudentService
    @Pointcut("execution(* com.craft.aop.service.StudentService.*(..))")
    public void studentServiceMethods() {
    }

    // runs BEFORE the method
    @Before("studentServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("  [Before]         " + joinPoint.getSignature().getName()
                + " called with " + Arrays.toString(joinPoint.getArgs()));
    }

    // runs only if the method RETURNS normally
    @AfterReturning(pointcut = "studentServiceMethods()", returning = "result", argNames = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("  [AfterReturning] " + joinPoint.getSignature().getName()
                + " returned " + result);
    }

    // runs only if the method THROWS an exception
    @AfterThrowing(pointcut = "studentServiceMethods()", throwing = "ex", argNames = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("  [AfterThrowing]  " + joinPoint.getSignature().getName()
                + " threw " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
    }

    // runs ALWAYS afterwards (like finally)
    @After("studentServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("  [After]          " + joinPoint.getSignature().getName() + " finished");
    }
}
