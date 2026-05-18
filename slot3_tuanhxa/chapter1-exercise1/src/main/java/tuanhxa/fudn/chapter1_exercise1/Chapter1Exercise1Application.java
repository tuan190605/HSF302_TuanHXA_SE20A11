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

		System.out.println("\n====== 1. TEST CREATE (THÊM MỚI - BÀI 1: 1-N) ======");
		Book book1 = new Book();
		book1.setTitle("Sách Bài 1 - Tập 1");
		book1.setAuthor("Tác giả A");
		book1.setIsbn("B1-ISBN-001");

		Book book2 = new Book();
		book2.setTitle("Sách Bài 1 - Tập 2");
		book2.setAuthor("Tác giả B");
		book2.setIsbn("B1-ISBN-002");

		Student s = new Student();
		s.setEmail("test_bai1@fe.edu.vn");
		s.setPassword("123456");
		s.setFirstName("Nguyen");
		s.setLastName("Van Bai Mot");
		s.setMarks(80);

		s.getBooks().add(book1);
		s.getBooks().add(book2);

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
		created.setFirstName("LeThi Bai Mot");
		created.setMarks(98);
		Student updated = studentService.update(created);
		System.out.println("Cập nhật thành công -> Tên mới: " + updated.getFirstName() + ", Điểm mới: " + updated.getMarks());


		System.out.println("\n====== 5. TEST DELETE (XÓA) ======");
		studentService.deleteById(updated.getId());
		System.out.println("Đã xóa sinh viên có ID: " + updated.getId());

		System.out.println("\n====== KẾT THÚC TEST BÀI 1 ======");
	}
}