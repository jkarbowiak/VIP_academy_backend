package org.itsurvival.books.rest;

import org.itsurvival.books.to.BookTo;
import org.itsurvival.books.common.Genre;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@RequestMapping("/services")
@RestController
public class BooksRestService {

    private final List<BookTo> books = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void addTestBooks() {
        BookTo book1 = new BookTo();
        book1.setId(1L);
        book1.setVersion(0);
        book1.setGenre(Genre.IT);
        book1.setYear(1999);
        book1.setTitle("Code Complete");
        book1.setAuthor("Steve McConnell");
        books.add(book1);

        BookTo book2 = new BookTo();
        book2.setId(2L);
        book2.setVersion(0);
        book2.setGenre(Genre.IT);
        book2.setYear(2001);
        book2.setTitle("Python. Wprowadzenie");
        book2.setAuthor("Mark Lutz, David Ascher");
        books.add(book2);
    }


    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public List<BookTo> findBooks() {
        return books;
    }

    @RequestMapping(path = "/book", method = RequestMethod.POST)
    public BookTo addBook(@RequestBody BookTo book) {
        books.add(book);
        return book;
    }

    @RequestMapping(path = "/book", method = RequestMethod.PUT)
    public BookTo updateBook(@RequestBody BookTo book) {
        BookTo bookToUpdate = books.stream().filter(b-> Objects.equals(b.getId(), book.getId())).findAny().orElseThrow(() -> new IllegalArgumentException("Book with given id does not exist"));

        int oldBookIndex = books.indexOf(bookToUpdate);
        books.remove(oldBookIndex);
        books.add(oldBookIndex, book);

        return book;
    }

    @RequestMapping(path = "/book/{id}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable("id") Long id) {
        return books.removeIf(b -> Objects.equals(b.getId(), id));
    }

}
