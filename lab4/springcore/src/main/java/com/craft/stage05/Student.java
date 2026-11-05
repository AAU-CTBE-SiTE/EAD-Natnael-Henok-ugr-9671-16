package com.craft.stage05;

import lombok.Data;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Data
@ToString
public class Student {
    private int id;
    private String name;
    private int age;

//    public Student() {
//        System.out.println("Student Constructor Called");
//    }

    // @ConstructorProperties lets <constructor-arg name="..."/> work even if the IDE
    // compiles without the -parameters flag. It does not change how the class behaves.
    @ConstructorProperties({"id", "name", "age"})
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        System.out.println("Student Parameterized Constructor Called");
    }

    public void getDetails() {
        System.out.println("Student Details " + this);
    }
}
