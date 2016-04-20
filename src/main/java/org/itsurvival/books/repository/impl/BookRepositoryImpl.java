package org.itsurvival.books.repository.impl;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.entity.Book;
import org.itsurvival.books.repository.BookSearchRepository;

import java.util.Collections;
import java.util.List;

public class BookRepositoryImpl implements BookSearchRepository {

    @Override
    public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
        return Collections.emptyList();
    }
}
