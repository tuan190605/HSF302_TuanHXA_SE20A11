package org.example.services;

import org.example.pojos.Student;

public class StudentServiceImpl implements StudentService{
    @Override
    public void save(Student student) {
        System.out.println("Save Student...");
    }
}
