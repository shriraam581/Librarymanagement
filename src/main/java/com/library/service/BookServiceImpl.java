// src/main/java/com/library/service/BookServiceImpl.java
package com.library.service;

import com.library.model.Book;
import com.library.model.Author;
import com.library.repository.BookRepository;
import com.library.repository.AuthorRepository;
import com.library.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Book saveBook(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId())
            .orElseThrow(() -> new RuntimeException("Author not found"));
        
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(author);
        book.setIsbn(bookDTO.getIsbn());
        book.setPublisher(bookDTO.getPublisher());
        book.setPublicationDate(bookDTO.getPublicationDate());
        book.setQuantity(bookDTO.getQuantity());
        book.setAvailableQuantity(bookDTO.getQuantity());
        
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Override
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = getBookById(id);
        Author author = authorRepository.findById(bookDTO.getAuthorId())
            .orElseThrow(() -> new RuntimeException("Author not found"));
        
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(author);
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setPublisher(bookDTO.getPublisher());
        existingBook.setPublicationDate(bookDTO.getPublicationDate());
        
        int quantityDifference = bookDTO.getQuantity() - existingBook.getQuantity();
        existingBook.setQuantity(bookDTO.getQuantity());
        existingBook.setAvailableQuantity(existingBook.getAvailableQuantity() + quantityDifference);
        
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}