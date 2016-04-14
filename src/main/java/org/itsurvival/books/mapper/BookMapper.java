package org.itsurvival.books.mapper;

import org.itsurvival.books.entity.Book;
import org.itsurvival.books.to.BookTo;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookMapper extends AbstractMapper<Book, BookTo> {

    @Override
    public BookTo convertToTransportObject(Book source) {
        BookTo book = null;
        if (source != null) {
            book = new BookTo();
            book.setId(source.getId());
            book.setTitle(source.getTitle());
            book.setAuthor(source.getAuthors().stream().map(author -> author.getPersonalData().getLastName()).collect(Collectors.joining(", ")));
            book.setGenre(source.getGenre());
            book.setVersion(source.getVersion());
        }
        return book;
    }

    @Override
    public Book convertToEntity(BookTo target) {
        Book book = null;
        if (target != null) {
            book = new Book(target.getId(), target.getTitle());
            // TODO authors
            book.setVersion(target.getVersion());
        }
        return book;
    }

}
