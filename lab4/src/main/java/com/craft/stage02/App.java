package com.craft.stage02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Let Spring manage object creation. */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("stage02/beansConfig.xml");
        Student student = context.getBean("student", Student.class);
        student.getHello();
    }
}
