package com.fudn.slot2.demo_jpa1.service;

import com.fudn.slot2.demo_jpa1.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(String name, String email, int age){
        Student s = new Student(name,email,age);
        em.persist(s);
        System.out.println("Save with ID = "+ s.getId());
    }
    @Transactional(readOnly = true)
    public void printAll(){
        em.createQuery("SELECT s FROM Student s",Student.class).getResultList().forEach(System.out::println);
    }
    @Transactional
    public void updateStudent(Long id, String newName, String newEmail, int newAge) {
        Student s = em.find(Student.class, id);

        if (s != null) {
            s.setName(newName);
            s.setEmail(newEmail);
            s.setAge(newAge);
            System.out.println("Updated student with ID = " + id);
        } else {
            System.out.println("Student with ID = " + id + " not found.");
        }
    }
}
