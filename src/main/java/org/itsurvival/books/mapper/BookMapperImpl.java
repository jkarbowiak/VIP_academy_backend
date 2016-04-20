package org.itsurvival.books.mapper;

import org.itsurvival.books.entity.Book;
import org.itsurvival.books.to.BookTo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapperImpl implements  BookMapperS {

    @Override
    public BookTo bookToBookTo(Book book) {
        return null;
    }

    @Override
    public Book bookToToBook(BookTo book) {
        return null;
    }

    @Override
    public List<BookTo> booksToBookTos(List<Book> books) {
        return null;
    }
}
