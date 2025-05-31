// src/main/java/com/library/repository/BookRepository.java
package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Find books by author ID
    List<Book> findByAuthorId(Long authorId);
    
    // Find available books
    @Query("SELECT b FROM Book b WHERE b.availableQuantity > 0")
    List<Book> findAvailableBooks();
    
    // Search by title containing (case insensitive)
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Find by ISBN
    Book findByIsbn(String isbn);
    
    // Custom query with join
    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.name LIKE %:authorName%")
    List<Book> findByAuthorNameContaining(@Param("authorName") String authorName);
}