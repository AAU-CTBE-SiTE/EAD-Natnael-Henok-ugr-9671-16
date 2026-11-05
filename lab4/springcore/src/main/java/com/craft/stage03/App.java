package com.craft.stage03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Managing beans via Spring's IoC container: we still set the values ourselves. */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("stage03/beansConfig.xml");
        Student student = context.getBean("student", Student.class);

        student.setId(101);
        student.setName("John");
        student.setAge(34);

        student.getDetails();
    }
}
