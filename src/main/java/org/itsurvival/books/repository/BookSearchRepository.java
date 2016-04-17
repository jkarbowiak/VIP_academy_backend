package org.itsurvival.books.repository;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.entity.Book;

import java.util.List;

public interface BookSearchRepository {

    List<Book> findBooks(BookSearchCriteria bookSearchCriteria);
}
