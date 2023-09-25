package com.librarymanagementsystem;

import java.util.List;

import com.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.librarymanagementsystem.entity.Author;
import com.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.entity.Category;
import com.librarymanagementsystem.entity.Publisher;
import com.librarymanagementsystem.entity.Role;
import com.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.service.BookService;

@SpringBootApplication
public class Application {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

            Book book = new Book("9781525896651", "Butcher Of Sorrow", "2W92MCSE", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
			book.addAuthors(new Author("Matt", "dummy description"));
			book.addCategories(new Category("Dummy categary"));
			book.addPublishers(new Publisher("Dummy publisher"));
			bookService.createBook(book);

            Book book1 = new Book("9793566397325", "Pirate Of The Land", "WVFPEQ4A", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
			book1.addAuthors(new Author("Maxwell", "Test description1"));
			book1.addCategories(new Category("New category"));
			book1.addPublishers(new Publisher("publisher2"));
			bookService.createBook(book1);

            Book book2 = new Book("9795542019641", "Destiny Of History", "WYM9CUDK", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
			book2.addAuthors(new Author("Josh Lang", "Test description2"));
			book2.addCategories(new Category("Spring category"));
			book2.addPublishers(new Publisher("publisher3"));
			bookService.createBook(book2);

            User user = new User(
					"admin",
					"admin",
					"admin",
					passwordEncoder.encode("admin"),
                    List.of(new Role("ROLE_ADMIN")));

			userRepository.save(user);
		};
	}
}
