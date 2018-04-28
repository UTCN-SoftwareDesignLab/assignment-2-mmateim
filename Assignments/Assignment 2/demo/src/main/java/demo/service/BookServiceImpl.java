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
    public Book create(BookDto bookDto) {
        Book b = new Book(bookDto.name, bookDto.author, bookDto.isbn);
        return bookRepository.save(b);
    }

    //public Book findById(int id){ return bookRepository.findOne(id);}
}