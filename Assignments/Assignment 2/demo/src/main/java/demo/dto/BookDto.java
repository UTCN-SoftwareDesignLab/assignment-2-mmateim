package demo.dto;

import demo.entity.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookDto {
   // @Size(min = 2)
   public String name;
    //@Pattern(regexp = "^[1-9]+$")
   // @Size(min = 5, max = 5, message = "ISBN is the wrong size")
    public String isbn;
    //@Size(min = 2)
    public String author;
    public String genre;
    public Float price;
    public Integer quantity;

    public BookDto() {
    }

    public BookDto(Book book){
        name = book.getName();
        isbn = book.getIsbn();
        author = book.getAuthor();
        genre = book.getGenre();
        price = book.getPrice();
        quantity = book.getQuantity();
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
