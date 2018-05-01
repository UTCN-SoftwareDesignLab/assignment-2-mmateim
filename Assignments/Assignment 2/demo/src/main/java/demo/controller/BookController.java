package demo.controller;

import demo.entity.Book;
import demo.entity.Sale;
import demo.entity.User;
import demo.service.BookService;
import demo.service.SaleService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private UserService userService;

    private List<Book> originalBooks;

    @RequestMapping(method = RequestMethod.GET)
    public String getTable(Model model) {
        System.out.println("BookController : return books.html");
        originalBooks = bookService.getAll();
        model.addAttribute("books", originalBooks);
        model.addAttribute("filteredBooks", originalBooks);
        model.addAttribute("message", "");
        return "books";
    }

    @RequestMapping(params = "filter=")
    public String searchBook(Model model, @RequestParam("genre") String genre,
                             @RequestParam("name") String name, @RequestParam("author") String author) {
        List<Book> result;
        if (name.equals("")) {
            if (author.equals("")) {
                System.out.println("BookController : filter by genre " + genre);
                result = bookService.findByGenre(genre);
            } else {
                System.out.println("BookController : filter by genre and author " + genre + ", " + author);
                result = bookService.findByGenreAndAuthor(genre, author);
            }
        } else {
            System.out.println("BookController : filter by genre and title " + genre + ", " + name);
            result = bookService.findByGenreAndName(genre, name);
        }
        model.addAttribute("filteredBooks", result);
        model.addAttribute("books", originalBooks);
        model.addAttribute("message", "");
        return "books";
    }

    @RequestMapping(params = "sell=")
    public String searchBook(Model model, @RequestParam("id") Integer id, @RequestParam("amount") Integer amount) {
        String message = "";
        if (id == null || amount == null) {
            message = "ID or amount field is empty";
        } else {
            Book book = bookService.findById(id);
            if (book == null) {
                message = "ID or amount field is empty";
            } else {
                if (book.getQuantity() < amount) {
                    message = "Not enough books on stock";
                } else {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    String username = auth.getName();
                    User user = userService.findByUsername(username);
                    if (user != null) {
                        Sale sale = new Sale(user.getId(), amount, new Date().getTime(), id);
                        if (saleService.create(sale) == null) {
                            message = "Error while registering sale";
                        } else {
                            book.setQuantity(book.getQuantity() - amount);
                            if (bookService.update(book) != null) {
                                originalBooks = bookService.getAll();
                                message = "Book sold";
                            } else {
                                message = "Error while updating book";
                            }
                        }
                    } else {
                        message = "Null user";
                    }
                }
            }
        }
        if (message.equals("Book sold")) {
            System.out.println("BookController : sell : book sold");
        } else {
            System.out.println("BookController : sell : error " + message);
        }
        model.addAttribute("message", message);
        model.addAttribute("books", originalBooks);
        model.addAttribute("filteredBooks", originalBooks);
        return "books";
    }
}