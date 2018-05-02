package demo.Validator;

import demo.dto.BookDto;
import demo.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookValidator {
    private Book book;
    private List<String> errors;

    public BookValidator(Book book) {
        this.book = book;
        this.errors = new ArrayList<>();
    }

    public List<String> validate(){
        if(book.getName().length() < 3) {
            errors.add("Book name too short");
        }
        if(book.getQuantity() < 0){
            errors.add("Quantity can not be negative");
        }
        if(book.getAuthor().length() < 5){
            errors.add("Author name too short");
        }
        if(book.getPrice() < 0){
            errors.add("Price can not be negative");
        }
        return errors;
    }
}
