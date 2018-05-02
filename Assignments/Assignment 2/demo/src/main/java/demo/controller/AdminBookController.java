package demo.controller;

import demo.Validator.BookValidator;
import demo.dto.BookDto;
import demo.entity.Book;
import demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/admin-books")
public class AdminBookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getTable(Model model) {
        System.out.println("AdminBookController : return books-admin.html");
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("book", new Book());
        model.addAttribute("message", "");
        return "books-admin";
    }

    @RequestMapping(params = "create=", method = RequestMethod.POST)
    public String createBook(Model model, @ModelAttribute("book") Book book) {
        System.out.println("AdminBookController : create");
        String message;
        try {
            BookValidator validator = new BookValidator(book);
            List<String> errors = validator.validate();
            if (errors.size() != 0) {
                message = errors.toString();
            } else {
                message = "";
            }
        } catch (Exception e) {
            message = "error : some fields are empty";
        }
        if (message.equals("")) {
            if (bookService.create(book) == null) {
                message = "Error sql table book";
            } else {
                message = "";
            }
        }
        if (message.equals("")) {
            model.addAttribute("book", new Book());
        } else {
            model.addAttribute("book", book);
        }
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("message", message);
        return "books-admin";
    }

    @RequestMapping(params = "update=", method = RequestMethod.POST)
    public String updateBook(Model model, @ModelAttribute("book") Book book) {
        System.out.println("AdminBookController : update");
        String message;
        try {
            BookValidator validator = new BookValidator(book);
            List<String> errors = validator.validate();
            if (errors.size() != 0) {
                message = errors.toString();
            } else {
                message = "";
            }
        } catch (Exception e) {
            message = "error : some fields are empty";
        }
        if (message.equals("")) {
            if (bookService.update(book) == null) {
                message = "Error sql table book";
            } else {
                message = "";
            }
        }
        if (message.equals("")) {
            model.addAttribute("book", new Book());
        } else {
            model.addAttribute("book", book);
        }
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("message", message);
        return "books-admin";
    }

    @RequestMapping(params = "delete=", method = RequestMethod.GET)
    public String deleteBook(Model model, @RequestParam("deleteId") Integer deleteId) {
        System.out.println("AdminBookController : delete");
        String message = "";
        if (deleteId != null) {
            bookService.delete(deleteId);
        } else {
            message = "Id field is empty";
        }
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("message", message);
        model.addAttribute("book", new Book());
        return "books-admin";
    }
}
