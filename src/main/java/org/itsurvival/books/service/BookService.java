package org.itsurvival.books.service;

import org.itsurvival.books.to.BookTo;

import java.util.List;

public interface BookService {

    List<BookTo> findBooks();

    BookTo addBook(BookTo book);

    BookTo updateBook(BookTo book);

    boolean deleteBook(Long id);
    
}
