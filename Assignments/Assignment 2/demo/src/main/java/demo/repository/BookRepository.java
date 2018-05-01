package demo.repository;

import demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>{
    public List<Book> findByGenre(String genre);
    public List<Book> findByGenreAndName(String genre, String name);
    public List<Book> findByGenreAndAuthor(String genre, String author);
}
