package demo.repository;

import demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(String genre);

    List<Book> findByGenreAndName(String genre, String name);

    List<Book> findByGenreAndAuthor(String genre, String author);

    List<Book> findByQuantity(Integer quantity);

    Book findById(int id);

    void delete(int id);
}
