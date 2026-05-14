package com.fudn.slot2.demo_jpa1;

import com.fudn.slot2.demo_jpa1.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoJpa1Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoJpa1Application.class, args);
	}
	@Bean
	public CommandLineRunner demo(StudentService service) {
		return args -> {
			service.createStudent("Nguyễn Văn A", "a@fpt.edu.vn", 20);
			service.createStudent("Trần Thị B", "b@fpt.edu.vn", 21);
			service.printAll();
		};
	}
}
