package com.craft;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class App {

    // Created once for the whole application
    public static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // ======================= CRUD (EntityManager API) =======================

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
            student.setTech(newTech); // fixed: the lab notes called setName(newTech) here
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

    // ============================== JPQL: SELECT ==============================

    // 1. Select all students
    public static List<Student> getAllStudents() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    // 2. Select student by ID (JPQL version)
    public static Student getStudentByIdJpql(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query =
                    em.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // 3. Select students by name (exact match)
    public static List<Student> getStudentsByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query =
                    em.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 4. Select students by name (case-insensitive)
    public static List<Student> getStudentsByNameCaseInsensitive(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE LOWER(s.name) = LOWER(:name)", Student.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 5. Select students by technology
    public static List<Student> getStudentsByTech(String tech) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query =
                    em.createQuery("SELECT s FROM Student s WHERE s.tech = :tech", Student.class);
            query.setParameter("tech", tech);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 6. Select students with names starting with a letter
    public static List<Student> getStudentsByNameStartingWith(String letter) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.name LIKE :pattern", Student.class);
            query.setParameter("pattern", letter + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 7. Select students with names containing a substring
    public static List<Student> getStudentsByNameContaining(String substring) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.name LIKE :pattern", Student.class);
            query.setParameter("pattern", "%" + substring + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 8. Select students with ID greater than a value
    public static List<Student> getStudentsWithIdGreaterThan(int minId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.id > :minId", Student.class);
            query.setParameter("minId", minId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 9. Select students with ID in a range
    public static List<Student> getStudentsWithIdBetween(int startId, int endId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.id BETWEEN :startId AND :endId", Student.class);
            query.setParameter("startId", startId);
            query.setParameter("endId", endId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 10. Select students with specific IDs (IN clause)
    public static List<Student> getStudentsByIdList(List<Integer> idList) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.id IN :idList", Student.class);
            query.setParameter("idList", idList);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 11. Select all students ordered by name (ascending)
    public static List<Student> getAllStudentsOrderByNameAsc() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s ORDER BY s.name ASC", Student.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 12. Select all students ordered by name (descending)
    public static List<Student> getAllStudentsOrderByNameDesc() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s ORDER BY s.name DESC", Student.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 13. Select first N students
    public static List<Student> getFirstNStudents(int n) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query =
                    em.createQuery("SELECT s FROM Student s ORDER BY s.id ASC", Student.class);
            query.setMaxResults(n);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 14. Select students with pagination
    public static List<Student> getStudentsWithPagination(int offset, int limit) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query =
                    em.createQuery("SELECT s FROM Student s ORDER BY s.id ASC", Student.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 15. Retrieve all student names (projection, so the result type is String, not Student)
    public static List<String> getAllStudentNames() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s.name FROM Student s", String.class).getResultList();
        } finally {
            em.close();
        }
    }

    // ========================= JPQL: aggregates and grouping =========================

    // 16. Count all students
    public static long countAllStudents() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(s) FROM Student s", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    // 17. Count students by technology
    public static List<Object[]> countStudentsByTech() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT s.tech, COUNT(s) FROM Student s GROUP BY s.tech", Object[].class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 18. Find distinct technologies
    public static List<String> getDistinctTechs() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT DISTINCT s.tech FROM Student s", String.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 19. Find average ID
    public static double getAverageId() {
        EntityManager em = getEntityManager();
        try {
            Double avg = em.createQuery("SELECT AVG(s.id) FROM Student s", Double.class)
                    .getSingleResult();
            return avg == null ? 0.0 : avg;
        } finally {
            em.close();
        }
    }

    // 20. Find maximum ID
    public static int getMaxId() {
        EntityManager em = getEntityManager();
        try {
            Integer max = em.createQuery("SELECT MAX(s.id) FROM Student s", Integer.class)
                    .getSingleResult();
            return max == null ? 0 : max;
        } finally {
            em.close();
        }
    }

    // 21. Find students with the longest names
    public static List<Student> getStudentsWithLongestNames() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT s FROM Student s WHERE LENGTH(s.name) = " +
                    "(SELECT MAX(LENGTH(s2.name)) FROM Student s2)", Student.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 22. Select students by technology and name pattern
    public static List<Student> getStudentsByTechAndNamePattern(String tech, String pattern) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s WHERE s.tech = :tech AND s.name LIKE :pattern",
                    Student.class);
            query.setParameter("tech", tech);
            query.setParameter("pattern", "%" + pattern + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 23. Select students with NULL technology
    public static List<Student> getStudentsWithNullTech() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.tech IS NULL", Student.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 24. Select students with non-NULL technology
    public static List<Student> getStudentsWithNonNullTech() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.tech IS NOT NULL", Student.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // ============================ JPQL: UPDATE / DELETE ============================

    // 25. Update a student's technology
    public static int updateStudentTech(int id, String newTech) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE Student s SET s.tech = :newTech WHERE s.id = :id");
            query.setParameter("newTech", newTech);
            query.setParameter("id", id);
            int updated = query.executeUpdate();
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }

    // 26. Update technology of all students matching a pattern
    public static int updateTechByPattern(String oldPattern, String newTech) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(
                    "UPDATE Student s SET s.tech = :newTech WHERE s.tech LIKE :oldPattern");
            query.setParameter("newTech", newTech);
            query.setParameter("oldPattern", "%" + oldPattern + "%");
            int updated = query.executeUpdate();
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }

    // 27. Delete student by ID (JPQL version)
    public static int deleteStudentByIdJpql(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Student s WHERE s.id = :id");
            query.setParameter("id", id);
            int deleted = query.executeUpdate();
            em.getTransaction().commit();
            return deleted;
        } finally {
            em.close();
        }
    }

    // 28. Delete students by technology
    public static int deleteStudentsByTech(String tech) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Student s WHERE s.tech = :tech");
            query.setParameter("tech", tech);
            int deleted = query.executeUpdate();
            em.getTransaction().commit();
            return deleted;
        } finally {
            em.close();
        }
    }

    // ======================== JPQL: subqueries ========================

    // 29. Students with the most common technology.
    // Fixed: the lab version used a multi-row subquery with ORDER BY, which is not valid JPQL.
    // Two steps instead: find the most common tech, then fetch its students.
    public static List<Student> getStudentsWithMostCommonTech() {
        EntityManager em = getEntityManager();
        try {
            List<String> top = em.createQuery(
                    "SELECT s.tech FROM Student s WHERE s.tech IS NOT NULL " +
                    "GROUP BY s.tech ORDER BY COUNT(s) DESC", String.class)
                    .setMaxResults(1)
                    .getResultList();
            if (top.isEmpty()) {
                return List.of();
            }
            return em.createQuery("SELECT s FROM Student s WHERE s.tech = :tech", Student.class)
                    .setParameter("tech", top.get(0))
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // 30. Students with unique technologies
    public static List<Student> getStudentsWithUniqueTechs() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT s FROM Student s WHERE s.tech IN (" +
                    "SELECT s2.tech FROM Student s2 GROUP BY s2.tech HAVING COUNT(s2) = 1)",
                    Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    // 31. Students with name length greater than the average
    public static List<Student> getStudentsWithLongerThanAverageNames() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT s FROM Student s WHERE LENGTH(s.name) > " +
                    "(SELECT AVG(LENGTH(s2.name)) FROM Student s2)", Student.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // ================================ main ================================

    public static void main(String[] args) {
        try {
            // Create
            Student s1 = new Student();
            s1.setName("John Snow");
            s1.setTech("Java");
            createStudent(s1);

            Student s2 = new Student();
            s2.setName("Mohammed Ali");
            s2.setTech("Python");
            createStudent(s2);

            // Read
            System.out.println("By id: " + getStudentById(s1.getId()));

            // Update
            updateStudentName("Alemayehu", s2.getId());
            System.out.println("After update: " + getStudentById(s2.getId()));

            // JPQL
            System.out.println("All students: " + getAllStudents());
            System.out.println("Names: " + getAllStudentNames());
            System.out.println("Java students: " + getStudentsByTech("Java"));
            System.out.println("Count: " + countAllStudents());
        } finally {
            emf.close();
        }
    }
}
