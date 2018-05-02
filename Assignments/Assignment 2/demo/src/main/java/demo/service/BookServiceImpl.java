package demo.service;

import demo.dto.BookDto;
import demo.entity.Book;
import demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, @Value("${bookService.maxBooks}") Integer maxBooks) {
        this.bookRepository = bookRepository;
        System.out.println(maxBooks);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> findByGenreAndName(String genre, String name) {
        return bookRepository.findByGenreAndName(genre, name);
    }

    @Override
    public List<Book> findByGenreAndAuthor(String genre, String author) {
        return bookRepository.findByGenreAndAuthor(genre, author);
    }

    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }
}