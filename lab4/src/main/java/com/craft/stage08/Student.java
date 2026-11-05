package com.craft.stage08;

import lombok.Data;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Data
@ToString
public class Student {
    private int id;
    private String name;
    private int age;
    private Address address;

    @ConstructorProperties({"id", "name", "age", "address"})
    public Student(int id, String name, int age, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
