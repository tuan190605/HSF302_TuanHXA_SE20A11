package com.fudn.slot2.demo_jpa1;

import com.fudn.slot2.demo_jpa1.entity.Student;
import com.fudn.slot2.demo_jpa1.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class StudentTest {
    @Autowired
    private StudentService studentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreateAndRetrieveStudentFromDatabase() {
        studentService.createStudent("Test Student", "test@fpt.edu.vn", 25);
        entityManager.flush();
        entityManager.clear();

        Student retrievedStudent = entityManager.find(Student.class, 3L);

        assertNotNull(retrievedStudent, "Student should exist in database");
        assertEquals(3L, retrievedStudent.getId());
        assertEquals("Test Student", retrievedStudent.getName());
        assertEquals("test@fpt.edu.vn", retrievedStudent.getEmail());
        assertEquals(25, retrievedStudent.getAge());
    }
    @Test
    public void testUpdateStudent() {
        studentService.createStudent("Old Name", "old@fpt.edu.vn", 20);
        entityManager.flush();
        entityManager.clear();
        Long id = 1L;

        studentService.updateStudent(id, "Updated Name", "updated@fpt.edu.vn", 22);
        entityManager.flush();
        entityManager.clear();
        Student updatedStudent = entityManager.find(Student.class, id);

        assertNotNull(updatedStudent);
        assertEquals("Updated Name", updatedStudent.getName());
        assertEquals("updated@fpt.edu.vn", updatedStudent.getEmail());
        assertEquals(22, updatedStudent.getAge());
    }
}
