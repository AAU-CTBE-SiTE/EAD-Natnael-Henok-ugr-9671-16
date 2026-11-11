package com.craft.aop.aspect;

import com.craft.aop.annotation.Audit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** Binds the annotation itself, so the aspect can read @Audit("...") */
@Aspect
@Component
@Order(4)
public class AuditAspect {

    @Before(value = "@annotation(audit)", argNames = "audit")
    public void audit(JoinPoint joinPoint, Audit audit) {
        System.out.println("  [Audit]          action=" + audit.value()
                + " on " + joinPoint.getSignature().toShortString());
    }
}
