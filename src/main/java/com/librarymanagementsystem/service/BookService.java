package com.librarymanagementsystem.service;

import java.util.List;

import com.librarymanagementsystem.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

	public List<Book> findAllBooks();

	public List<Book> searchBooks(String keyword);

	public Book findBookById(Long id);

	public void createBook(Book book);

	public void updateBook(Book book);

	public void deleteBook(Long id);

	public Page<Book> findPaginated(Pageable pageable);

}
