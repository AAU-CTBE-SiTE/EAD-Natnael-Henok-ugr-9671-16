package com.craft.stage10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Bean scopes: singleton (default) vs prototype. */
public class App {
    public static void main(String[] args) {
        run("Singleton scope", "stage10/beansConfig.xml");
        run("Prototype scope", "stage10/beansConfig-prototype.xml");
    }

    private static void run(String title, String config) {
        System.out.println("-- " + title);
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        Student student1 = context.getBean("student", Student.class);
        Student student2 = context.getBean("student", Student.class);

        // Check if both references point to the SAME instance
        System.out.println(student1 == student2);

        // Modify student1's name
        student1.setName("John Doe");

        // Check student2 (singleton: reflects the change, prototype: null)
        System.out.println(student2.getName());
    }
}
