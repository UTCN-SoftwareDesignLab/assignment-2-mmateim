package demo.controller;

import demo.dto.BookDto;
import demo.entity.Book;
import demo.service.BookService;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getTable(Model model) {
        System.out.println("BookController : return books.html");
        model.addAttribute("books", service.getAll());
        model.addAttribute("filteredBooks", service.getAll());
        return "books";
    }

    @RequestMapping(params = "filter=")
    public String searchBook(Model model, @RequestParam("genre") String genre,
                             @RequestParam("name") String name, @RequestParam("author") String author) {
        List<Book> result;
        if (name.equals("")) {
            if (author.equals("")) {
                result = service.findByGenre(genre);
            } else {
                result = service.findByGenreAndAuthor(genre, author);
            }
        } else {
            result = service.findByGenreAndName(genre, name);
        }
        model.addAttribute("filteredBooks", result);
        model.addAttribute("books", service.getAll());
        return "books";
    }

//    @ResponseBody
//    @RequestMapping(value = "/books-all", method = RequestMethod.GET)
//    public List<Book> getAll() {
//        System.out.println("BookController : return all books");
//        //model.addAttribute("books",service.getAll());
//        return service.getAll();
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/books-all", method = RequestMethod.POST)
//    public Book create(@RequestBody @Valid BookDto book) {
//        return service.create(book);
//    }
}