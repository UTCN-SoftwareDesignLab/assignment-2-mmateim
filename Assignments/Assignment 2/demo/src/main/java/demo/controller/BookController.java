package demo.controller;

import demo.dto.BookDto;
import demo.entity.Book;
import demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Book create(@RequestBody @Valid BookDto book) {
        return service.create(book);
    }
}