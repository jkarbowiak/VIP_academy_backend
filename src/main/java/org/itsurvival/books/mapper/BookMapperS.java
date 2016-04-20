package org.itsurvival.books.mapper;

import org.itsurvival.books.entity.Book;
import org.itsurvival.books.to.BookTo;

import java.util.List;

public interface BookMapperS {

  BookTo bookToBookTo(Book book);

  Book bookToToBook(BookTo book);

  List<BookTo> booksToBookTos(List<Book> books);

}
