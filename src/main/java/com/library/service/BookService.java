package com.library.service;

import com.library.model.Book;
import com.library.web.dto.BookDTO;

import java.util.List;

public interface BookService {
    Book saveBook(BookDTO bookDTO);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    List<Book> getBooksByAuthor(Long authorId);
    Book updateBook(Long id, BookDTO bookDTO);
	void deleteBook(Long id);
}
