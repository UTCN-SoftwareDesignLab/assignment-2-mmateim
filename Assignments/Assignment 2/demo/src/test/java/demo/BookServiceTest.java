package demo;

import demo.entity.Book;
import demo.repository.BookRepository;
import demo.service.BookService;
import demo.service.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceTest {

    BookService bookService;
    @Mock
    BookRepository bookRepository;

    @Before
    public void setup() {
        bookService = new BookServiceImpl(bookRepository, 300);
        List<Book> books = new ArrayList<Book>();
        Book book = new Book("012345", "Harry Potter", "J.K.Rowling", "fantasy", 4, 75.3f);
        books.add(book);
        Mockito.when(bookRepository.findByQuantity(0)).thenReturn(new ArrayList<>());
        Mockito.when(bookRepository.findByGenre("fantasy")).thenReturn(books);
        Mockito.when(bookRepository.findByGenreAndName("fantasy", "Harry Potter")).thenReturn(books);
    }

    @Test
    public void findByGenre() {
        List<Book> books = bookService.findByGenre("fantasy");
        Assert.assertTrue(books.size() == 1);
    }

    @Test
    public void findByGenreAndName() {
        List<Book> books = bookService.findByGenreAndName("fantasy", "Harry Potter");
        Assert.assertTrue(books.size() == 1);
    }

    @Test
    public void findEmptyStock() {
        List<Book> books = bookService.findEmptyStock();
        Assert.assertTrue(books.size() == 0);
    }


}
