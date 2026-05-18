package tuanhxa.fudn.chapter1_exercise1.repository;

import tuanhxa.fudn.chapter1_exercise1.dao.StudentDAO;
import tuanhxa.fudn.chapter1_exercise1.pojo.Student;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private final StudentDAO studentDAO;

    public StudentRepositoryImpl() {
        this.studentDAO = new StudentDAO();
    }

    public StudentRepositoryImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student save(Student student) {
        studentDAO.save(student);
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(studentDAO.findById(id));
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student update(Student student) {
        studentDAO.update(student);
        return student;
    }

    @Override
    public void deleteById(Long id) {
        studentDAO.delete(id);
    }
}