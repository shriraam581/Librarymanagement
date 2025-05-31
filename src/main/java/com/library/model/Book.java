package com.library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books", 
       uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    
    @Column(unique = true, length = 20)
    private String isbn;
    
    private String publisher;
    
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    
    @Column(nullable = false)
    private int quantity;
    
    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;
    
    @Column(name = "date_created")
    private LocalDate dateCreated;
    
    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    // Constructors
    public Book() {
        this.dateCreated = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }
    
    public Book(String title, Author author, String isbn, String publisher, 
                LocalDate publicationDate, int quantity) {
        this();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.lastUpdated = LocalDate.now();
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
        this.lastUpdated = LocalDate.now();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
        this.lastUpdated = LocalDate.now();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
        this.lastUpdated = LocalDate.now();
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
        this.lastUpdated = LocalDate.now();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.lastUpdated = LocalDate.now();
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
        this.lastUpdated = LocalDate.now();
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    // Business logic methods
    public boolean isAvailable() {
        return availableQuantity > 0;
    }
    
    public void borrowCopy() {
        if (availableQuantity <= 0) {
            throw new IllegalStateException("No available copies to borrow");
        }
        this.availableQuantity--;
        this.lastUpdated = LocalDate.now();
    }
    
    public void returnCopy() {
        if (availableQuantity >= quantity) {
            throw new IllegalStateException("Cannot return more copies than total quantity");
        }
        this.availableQuantity++;
        this.lastUpdated = LocalDate.now();
    }
 // In your Book.java model
    public void decreaseAvailableQuantity() {
        if (this.availableQuantity <= 0) {
            throw new IllegalStateException("No available copies");
        }
        this.availableQuantity--;
        this.lastUpdated = LocalDate.now();
    }

    public void increaseAvailableQuantity() {
        if (this.availableQuantity >= this.quantity) {
            throw new IllegalStateException("Cannot exceed total quantity");
        }
        this.availableQuantity++;
        this.lastUpdated = LocalDate.now();
    }

    // toString() for debugging
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + (author != null ? author.getName() : "null") +
                ", isbn='" + isbn + '\'' +
                ", available=" + availableQuantity + "/" + quantity +
                '}';
    }
}