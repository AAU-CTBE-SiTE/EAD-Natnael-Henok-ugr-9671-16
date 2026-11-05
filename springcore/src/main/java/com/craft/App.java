package com.craft;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Core lab, Day 1: IoC and bean management.
 * Each method below is one section of the lab. Run main() to see them all.
 */
public class App {

    public static void main(String[] args) {
        tightCoupling();
        basicBean();
        setterInjection();
        constructorInjection();
        objectInjection();
        collectionInjection();
        beanScopes();
    }

    private static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    // 1. The problem: App creates its own dependency with new
    private static void tightCoupling() {
        header("1. Tight coupling (no Spring)");
        Student student = new Student();
        student.getHello();
    }

    // 2. Let Spring create the object
    private static void basicBean() {
        header("2. Basic bean (Spring creates the object)");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beans-basic.xml")) {
            Student student = context.getBean("student", Student.class);
            student.getHello();
        }
    }

    // 3. Setter-based injection
    private static void setterInjection() {
        header("3. Setter injection");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beans-setter.xml")) {
            Student student = context.getBean("student", Student.class);
            student.getDetails();
            System.out.println(student);
        }
    }

    // 4. Constructor-based injection
    private static void constructorInjection() {
        header("4. Constructor injection");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beans-constructor.xml")) {
            System.out.println(context.getBean("studentByName", Student.class));
            System.out.println(context.getBean("studentByIndex", Student.class));
        }
    }

    // 5. Injecting another bean with ref
    private static void objectInjection() {
        header("5. Object dependency injection (ref)");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beans-object.xml")) {
            Student student = context.getBean("student", Student.class);
            System.out.println(student);
        }
    }

    // 6. Collections: list, set, array, map, props
    private static void collectionInjection() {
        header("6. Collection injection");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beans-collections.xml")) {
            Student student = context.getBean("student", Student.class);
            System.out.println(student);
            student.getDetails();

            CollectionBean bean = context.getBean("collections", CollectionBean.class);
            System.out.println("list   : " + bean.getList());
            System.out.println("set    : " + bean.getTags());
            System.out.println("array  : " + java.util.Arrays.toString(bean.getSubjectArray()));
            System.out.println("map    : " + bean.getCapitals());
            System.out.println("props  : " + bean.getSettings());
        }
    }

    // 7. Singleton vs prototype
    private static void beanScopes() {
        header("7. Bean scopes");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beans-scope.xml")) {

            System.out.println("-- singleton");
            Student s1 = context.getBean("singletonStudent", Student.class);
            Student s2 = context.getBean("singletonStudent", Student.class);
            System.out.println("s1 == s2 ? " + (s1 == s2));
            s1.setName("John Doe");
            System.out.println("s2 name after changing s1: " + s2.getName());

            System.out.println("-- prototype");
            Student p1 = context.getBean("prototypeStudent", Student.class);
            Student p2 = context.getBean("prototypeStudent", Student.class);
            System.out.println("p1 == p2 ? " + (p1 == p2));
            p1.setName("John Doe");
            System.out.println("p2 name after changing p1: " + p2.getName());
        }
    }
}
