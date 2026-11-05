package com.craft.stage05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Constructor-based injection: by name, with no name, and by index. */
public class App {
    public static void main(String[] args) {
        String[] configs = {
                "stage05/beansConfig.xml",         // by name
                "stage05/beansConfig-noname.xml",  // no name, no index
                "stage05/beansConfig-index.xml"    // by index
        };

        for (String config : configs) {
            System.out.println("-- " + config);
            ApplicationContext context = new ClassPathXmlApplicationContext(config);
            Student student = context.getBean("student", Student.class);
            System.out.println(student);
        }
    }
}
