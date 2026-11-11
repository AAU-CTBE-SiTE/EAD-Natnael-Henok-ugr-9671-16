package com.craft.aop;

import com.craft.aop.config.AppConfig;
import com.craft.aop.service.StudentService;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) throws Exception {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            StudentService service = context.getBean(StudentService.class);

            section("1. @Before, @AfterReturning and @After");
            service.getStudent(1);

            section("2. args binding: Validation + logging on addStudent");
            service.addStudent(3, "Sam Wilson");

            section("3. An aspect STOPS the call (blank name)");
            try {
                service.addStudent(4, " ");
            } catch (IllegalArgumentException e) {
                System.out.println("  Caught in App: " + e.getMessage());
            }

            section("4. @AfterThrowing (student 99 does not exist) + @Audit");
            try {
                service.deleteStudent(99);
            } catch (IllegalArgumentException e) {
                System.out.println("  Caught in App: " + e.getMessage());
            }

            section("5. @Around with the custom @TrackTime annotation");
            service.slowOperation();

            section("6. Custom @Audit annotation");
            service.updateStudent(1, "John Doe Jr.");

            section("7. How it works: the bean is really a proxy");
            System.out.println("  Class of the bean : " + service.getClass().getSimpleName());
            System.out.println("  Is it an AOP proxy: " + AopUtils.isAopProxy(service));
        }
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
