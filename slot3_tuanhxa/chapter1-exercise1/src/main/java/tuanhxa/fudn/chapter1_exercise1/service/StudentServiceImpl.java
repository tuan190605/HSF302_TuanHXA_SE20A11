package tuanhxa.fudn.chapter1_exercise1.service;

import tuanhxa.fudn.chapter1_exercise1.pojo.Student;
import tuanhxa.fudn.chapter1_exercise1.repository.StudentRepository;
import tuanhxa.fudn.chapter1_exercise1.repository.StudentRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl() {
        this.studentRepository = new StudentRepositoryImpl();
    }

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        validateStudent(student, false);
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> getById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student update(Student student) {
        validateStudent(student, true);
        return studentRepository.update(student);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        studentRepository.deleteById(id);
    }

    private void validateStudent(Student student, boolean requireId) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null");
        }

        if (requireId && (student.getId() == null || student.getId() <= 0)) {
            throw new IllegalArgumentException("Student id is required for update");
        }

        if (isBlank(student.getEmail())) {
            throw new IllegalArgumentException("Email must not be blank");
        }
        if (isBlank(student.getPassword())) {
            throw new IllegalArgumentException("Password must not be blank");
        }
        if (isBlank(student.getFirstName())) {
            throw new IllegalArgumentException("First name must not be blank");
        }
        if (isBlank(student.getLastName())) {
            throw new IllegalArgumentException("Last name must not be blank");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}