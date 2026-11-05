package com.craft.stage06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 1. Literal dependency injection: simple values (strings, numbers). */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("stage06/beansConfig.xml");

        Student student = context.getBean("student", Student.class);

        System.out.println(student);
        System.out.println(student.getName());
    }
}
