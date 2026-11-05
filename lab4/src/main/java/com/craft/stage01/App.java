package com.craft.stage01;

/** Problem: Tight Coupling. App creates its own Student with new. */
public class App {
    public static void main(String[] args) {
        Student student = new Student();
        student.getHello();
    }
}
