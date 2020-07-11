package com.learning.example.basic;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.learning.example.basic.dao.BookRepository;
import com.learning.example.basic.entity.Book;

@SpringBootApplication
public class SpringRestSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestSpringbootApplication.class, args);
	}

	// init bean to insert 3 books into h2 database.
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41")));
            repository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
            repository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));
        };
    }
}
