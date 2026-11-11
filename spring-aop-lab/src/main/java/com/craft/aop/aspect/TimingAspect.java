package com.craft.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Around is the most powerful advice: it wraps the method.
 * YOU decide when (and whether) the real method runs by calling proceed().
 */
@Aspect
@Component
@Order(3)
public class TimingAspect {

    // matches every method that carries the @TrackTime annotation
    @Around("@annotation(com.craft.aop.annotation.TrackTime)")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        System.out.println("  [Around]         starting " + joinPoint.getSignature().getName());
        try {
            return joinPoint.proceed();   // run the real method
        } finally {
            long millis = (System.nanoTime() - start) / 1_000_000;
            System.out.println("  [Around]         " + joinPoint.getSignature().getName()
                    + " took " + millis + " ms");
        }
    }
}
