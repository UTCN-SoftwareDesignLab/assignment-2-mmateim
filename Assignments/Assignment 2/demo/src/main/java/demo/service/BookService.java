package demo.service;

import demo.dto.BookDto;
import demo.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book create(BookDto book);

    List<Book> findByGenre(String genre);

    List<Book> findByGenreAndName(String genre, String name);

    List<Book> findByGenreAndAuthor(String genre, String author);

    Book findById(int id);

    Book update(Book book);
}
