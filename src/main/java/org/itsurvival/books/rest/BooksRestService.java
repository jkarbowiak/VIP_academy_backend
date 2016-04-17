package org.itsurvival.books.rest;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.service.BookService;
import org.itsurvival.books.to.BookTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/services")
@RestController
public class BooksRestService {

    private final BookService bookService;

    @Autowired
    public BooksRestService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public List<BookTo> findBooks(BookSearchCriteria bookSearchCriteria) {
        return bookService.findBooks(bookSearchCriteria);
    }

    @RequestMapping(path = "/book", method = RequestMethod.POST)
    public BookTo addBook(@RequestBody BookTo book) {
        return bookService.addBook(book);
    }

    @RequestMapping(path = "/book", method = RequestMethod.PUT)
    public BookTo updateBook(@RequestBody BookTo book) {
        return bookService.updateBook(book);
    }

    @RequestMapping(path = "/book/{id}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }

}
