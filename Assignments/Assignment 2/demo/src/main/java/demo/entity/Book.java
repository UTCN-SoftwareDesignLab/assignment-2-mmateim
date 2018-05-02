package demo.entity;

import javax.persistence.*;

/**
 * Created by Catalysts on 8/9/2015.
 */
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String isbn;
    private String name;
    private String author;
    private String genre;
    private Integer quantity;
    private Float price;

    public Book(String isbn, String name, String author, String genre, Integer quantity, Float price) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    public Book() {}

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "title " + name + " author " + author + " genre " + genre + " isbn " + isbn + " quantity " + quantity + " price " + price;
    }
}
