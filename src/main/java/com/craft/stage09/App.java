package com.craft.stage09;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Collections dependency injection: list, then the array and set variants. */
public class App {
    public static void main(String[] args) {
        String[] configs = {
                "stage09/beansConfig.xml",        // <list>
                "stage09/beansConfig-array.xml",  // <array>
                "stage09/beansConfig-set.xml"     // <set>
        };

        for (String config : configs) {
            System.out.println("-- " + config);
            ApplicationContext context = new ClassPathXmlApplicationContext(config);
            Student student = context.getBean("student", Student.class);

            System.out.println(student);

            student.getDetails();
        }
    }
}
