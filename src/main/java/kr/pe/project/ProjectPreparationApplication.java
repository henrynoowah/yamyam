package kr.pe.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ProjectPreparationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPreparationApplication.class, args);
	}

}
