package tuanhxa.fudn.chapter1_exercise1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tuanhxa.fudn.chapter1_exercise1.pojo.Student;

import java.util.List;

public class StudentDAO {

    private static final String PERSISTENCE_UNIT_NAME = "hsf302-chapter1";
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    // ======================== CREATE ========================
    public void save(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // ======================== READ ========================
    public Student findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s", Student.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // ======================== UPDATE ========================
    public void update(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student existingStudent = em.find(Student.class, student.getId());
            if (existingStudent != null) {
                existingStudent.setEmail(student.getEmail());
                existingStudent.setPassword(student.getPassword());
                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName(student.getLastName());
                existingStudent.setMarks(student.getMarks());
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // ======================== DELETE ========================
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // ======================== CLOSE ========================
    public static void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}