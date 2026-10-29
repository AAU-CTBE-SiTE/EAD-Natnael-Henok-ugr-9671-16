package com.craft;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class AppTest {
    @Test
    public void studentEntityWorks() {
        Student s = new Student();
        s.setName("John Snow");
        s.setTech("Java");
        assertNotNull(s.toString());
    }
}
