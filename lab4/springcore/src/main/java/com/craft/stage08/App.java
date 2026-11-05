package com.craft.stage08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Using Constructor: object injection. */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("stage08/beansConfig.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
