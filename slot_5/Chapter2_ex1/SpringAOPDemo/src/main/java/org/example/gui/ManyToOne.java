package org.example.gui;

import org.example.configs.AppConfig;
import org.example.pojos.Student;
import org.example.services.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ManyToOne {
    public static void main(String[] args) {
        // Nạp cấu hình Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean StudentService từ container
        StudentService myService = context.getBean(StudentService.class);

        // Tạo đối tượng và gọi hàm
        Student st = new Student("Lam", "Nguyen", 8);
        myService.save(st); // Hàm gọi thực tế

        context.close();
    }
}
