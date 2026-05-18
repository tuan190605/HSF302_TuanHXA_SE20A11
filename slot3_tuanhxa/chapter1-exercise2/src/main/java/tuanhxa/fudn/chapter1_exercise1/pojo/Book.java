package tuanhxa.fudn.chapter1_exercise1.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @ManyToMany(mappedBy = "books")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude

    private Set<Student> students = new HashSet<>();
    public void addStudent(Student student) {
        this.students.add(student);
        student.getBooks().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getBooks().remove(this);
    }
}