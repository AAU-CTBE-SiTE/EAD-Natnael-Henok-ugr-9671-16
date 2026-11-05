package com.craft.stage07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Object dependencies: the ref attribute injects another bean. */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("stage07/beansConfig.xml");
        Student student = context.getBean("student", Student.class);

        System.out.println(student);
    }
}
