package demo.service;

import demo.dto.BookDto;
import demo.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book create(BookDto book);
}
