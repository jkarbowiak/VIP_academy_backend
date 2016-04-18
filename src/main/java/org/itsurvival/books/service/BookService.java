package org.itsurvival.books.service;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.to.BookTo;

import java.util.List;

public interface BookService {

    List<BookTo> findBooks(BookSearchCriteria bookSearchCriteria);

    BookTo addBook(BookTo book);

    BookTo updateBook(BookTo book);

    BookTo readBook(long id);

    boolean deleteBook(long id);
    
}
