package com.craft;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private int id;
    private String name;
    private int age;
    private Address address;
    private List<Subject> subjects;

    // Used by setter injection, scopes, and plain "new Student()"
    public Student() {
        System.out.println("   [Student created with the empty constructor]");
    }

    // Used by constructor injection
    public Student(int id, String name, int age, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        System.out.println("   [Student created with the parameterized constructor]");
    }

    public void getHello() {
        System.out.println("Hello Springers");
    }

    public void getDetails() {
        System.out.println("Student Name: " + name);
        if (subjects == null) {
            System.out.println("No subjects");
            return;
        }
        int index = 1;
        for (Subject subject : subjects) {
            System.out.println("Subject " + index + ": " + subject.getSubjectName());
            index++;
        }
    }
}
