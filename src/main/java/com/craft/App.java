package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    // Created once for the whole application
    public static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create
    public static void createStudent(Student student) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Read
    public static Student getStudentById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    // Update (name only)
    public static void updateStudentName(String newName, int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            student.setName(newName);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Update (name and tech)
    public static void updateStudentNameAndTech(String newName, String newTech, int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            student.setName(newName);
            student.setTech(newTech);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Delete
    public static void deleteStudentById(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        try {
            Student student1 = new Student();
            student1.setName("Mohammed ali");
            student1.setTech("Python");

            createStudent(student1);                                  // Create
            System.out.println(getStudentById(student1.getId()));     // Read
            updateStudentName("Alemayehu", student1.getId());         // Update
            System.out.println(getStudentById(student1.getId()));
            // deleteStudentById(student1.getId());                   // Delete (uncomment to test)
        } finally {
            emf.close();
        }
    }
}
