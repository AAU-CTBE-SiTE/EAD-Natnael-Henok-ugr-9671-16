package com.craft.aop.service;

import com.craft.aop.annotation.Audit;
import com.craft.aop.annotation.TrackTime;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Plain business code. Notice there is NO logging, timing or auditing code in here.
 * The aspects add that behaviour from the outside.
 */
@Component
public class StudentService {

    private final Map<Integer, String> students = new LinkedHashMap<>();

    public StudentService() {
        students.put(1, "John Doe");
        students.put(2, "Jane Smith");
    }

    public String getStudent(int id) {
        String name = students.get(id);
        if (name == null) {
            throw new IllegalArgumentException("Student " + id + " not found");
        }
        return name;
    }

    public void addStudent(int id, String name) {
        students.put(id, name);
    }

    @Audit("UPDATE_STUDENT")
    public void updateStudent(int id, String name) {
        getStudentOrFail(id);
        students.put(id, name);
    }

    @Audit("DELETE_STUDENT")
    public void deleteStudent(int id) {
        getStudentOrFail(id);
        students.remove(id);
    }

    @TrackTime
    public void slowOperation() throws InterruptedException {
        Thread.sleep(300);   // pretend this is a heavy report
    }

    private void getStudentOrFail(int id) {
        if (!students.containsKey(id)) {
            throw new IllegalArgumentException("Student " + id + " not found");
        }
    }
}
