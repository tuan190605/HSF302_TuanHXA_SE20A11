package tuanhxa.fudn.chapter1_exercise1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tuanhxa.fudn.chapter1_exercise1.pojo.Book;
import tuanhxa.fudn.chapter1_exercise1.pojo.Student;
import tuanhxa.fudn.chapter1_exercise1.service.StudentService;
import tuanhxa.fudn.chapter1_exercise1.service.StudentServiceImpl;

import java.util.List;
import java.util.Optional;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Chapter1Exercise1Application {

	public static void main(String[] args) {

		SpringApplication.run(Chapter1Exercise1Application.class, args);
		StudentService studentService = new StudentServiceImpl();

		System.out.println("\n====== 1. TEST CREATE (THÊM MỚI) ======");
		Book book1 = new Book();
		book1.setTitle("Lập trình Java Cơ bản");
		book1.setAuthor("James Gosling");
		book1.setIsbn("ISBN-2001");

		Book book2 = new Book();
		book2.setTitle("Spring Boot Thực chiến");
		book2.setAuthor("Craig Walls");
		book2.setIsbn("ISBN-2002");

		Student s = new Student();
		s.setEmail("test_many2@fe.edu.vn");
		s.setPassword("123456");
		s.setFirstName("Nguyen");
		s.setLastName("Van Test");
		s.setMarks(90);

		s.addBook(book1);
		s.addBook(book2);

		Student created = studentService.create(s);
		System.out.println("Lưu thành công Sinh viên có ID: " + created.getId());
		System.out.println("Số sách sinh viên này đã mượn: " + created.getBooks().size());


		System.out.println("\n====== 2. TEST READ ALL (LẤY DANH SÁCH) ======");
		List<Student> all = studentService.getAll();
		System.out.println("Tổng số lượng sinh viên trong DB: " + all.size());


		System.out.println("\n====== 3. TEST READ BY ID (TÌM THEO ID) ======");
		Optional<Student> byId = studentService.getById(created.getId());
		System.out.println("Tìm thấy sinh viên: " + byId.orElse(null).getFirstName());


		System.out.println("\n====== 4. TEST UPDATE (CẬP NHẬT) ======");
		created.setFirstName("LeThi");
		created.setMarks(95);
		Student updated = studentService.update(created);
		System.out.println("Cập nhật thành công -> Tên mới: " + updated.getFirstName() + ", Điểm mới: " + updated.getMarks());


		System.out.println("\n====== 5. TEST DELETE (XÓA) ======");
		studentService.deleteById(updated.getId());
		System.out.println("Đã xóa sinh viên có ID: " + updated.getId());

		System.out.println("\n====== KẾT THÚC TEST TẤT CẢ CHỨC NĂNG ======");
	}
}