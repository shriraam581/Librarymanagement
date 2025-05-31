package com.library.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {  // This is a regular class, no implementation needed
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String biography;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
    
    // Constructors
    public Author() {
    }
    
    public Author(String name) {
        this.name = name;
    }
    
    public Author(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBiography() {
        return biography;
    }
    
    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    // Helper method for bidirectional relationship
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }
    
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}