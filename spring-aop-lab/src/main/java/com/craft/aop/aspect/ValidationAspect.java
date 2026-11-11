package com.craft.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * An aspect can look at the arguments (args(...)) and even STOP the call
 * by throwing an exception before the real method runs.
 */
@Aspect
@Component
@Order(2)
public class ValidationAspect {

    @Before(value = "execution(* com.craft.aop.service.StudentService.addStudent(..)) && args(id, name)",
            argNames = "id,name")
    public void validate(int id, String name) {
        System.out.println("  [Validation]     checking id=" + id + ", name='" + name + "'");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
    }
}
