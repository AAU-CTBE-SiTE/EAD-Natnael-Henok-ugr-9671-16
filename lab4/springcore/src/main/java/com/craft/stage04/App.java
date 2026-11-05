package com.craft.stage04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Setter-based injection: Spring calls setId, setName, setAge from the XML. */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("stage04/beansConfig.xml");
        Student student = context.getBean("student", Student.class);

        student.getDetails();

        // "Let's getName and print the bean"
        System.out.println("Student Name: " + student.getName());
        System.out.println(student);
    }
}
