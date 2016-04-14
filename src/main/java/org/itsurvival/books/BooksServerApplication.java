package org.itsurvival.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BooksServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksServerApplication.class, args);
	}
}
