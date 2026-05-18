package tuanhxa.fudn.chapter1_exercise1.service;

import tuanhxa.fudn.chapter1_exercise1.pojo.Student;

import java.util.List;
import java.util.Optional;
public interface StudentService {
    Student create(Student student);
    Optional<Student> getById(Long id);
    List<Student> getAll();
    Student update(Student student);
    void deleteById(Long id);
}
