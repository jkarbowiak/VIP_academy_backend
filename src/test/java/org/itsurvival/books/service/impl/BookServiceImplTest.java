package org.itsurvival.books.service.impl;

import com.google.common.collect.Sets;
import org.itsurvival.books.AbstractDatabaseTest;
import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.common.Genre;
import org.itsurvival.books.service.BookService;
import org.itsurvival.books.to.BookTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.fest.assertions.Assertions.assertThat;

public class BookServiceImplTest extends AbstractDatabaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void shouldFindAllBooksWhenSearchCriteriaEmpty() {

        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();

        // when
        List<BookTo> books = bookService.findBooks(bookSearchCriteria);

        // then
        assertThat(books).isNotEmpty();

        Set<Long> expectedIds = LongStream.range(1, 9).boxed().collect(Collectors.toSet());
        checkBookIds(books, expectedIds);
    }

    @Test
    public void shouldFindBooksByAuthor() {

        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
        bookSearchCriteria.setAuthor("Cob");

        // when
        List<BookTo> books = bookService.findBooks(bookSearchCriteria);

        // then
        Set<Long> expectedIds = Sets.newHashSet(1L, 2L, 3L, 4L);
        checkBookIds(books, expectedIds);
    }

    @Test
    public void shouldFindBooksByTitle() {

        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
        bookSearchCriteria.setTitle("Szko");

        // when
        List<BookTo> books = bookService.findBooks(bookSearchCriteria);

        // then
        Set<Long> expectedIds = Sets.newHashSet(6L, 7L);
        checkBookIds(books, expectedIds);
    }

    @Test
    public void shouldModifyVersionDuringUpdate() {
        // given
        long bookId = 1L;
        BookTo book = bookService.readBook(bookId);
        int year = 2000;
        book.setYear(year);

        // when
        BookTo updateResult = bookService.updateBook(book);

        // then
        assertThat(updateResult.getVersion()).isGreaterThan(book.getVersion());
        BookTo updatedBook = bookService.readBook(bookId);
        assertThat(updatedBook.getVersion()).isEqualTo(updateResult.getVersion());
        assertThat(updatedBook.getYear()).isEqualTo(year);
    }

    @Test
    public void shouldAddNewBook() {
        // given
        int year = 2001;
        String title = "Test title";
        Genre genre = Genre.CRIME;
        String author = "Coben";

        BookTo book = new BookTo();
        book.setYear(year);
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthor(author);

        // when
        BookTo addResult = bookService.addBook(book);

        // then
        Long id = addResult.getId();
        assertThat(id).isNotNull();

        BookTo addedBook = bookService.readBook(id);
        assertThat(addedBook.getTitle()).isEqualTo(title);
        assertThat(addedBook.getAuthor()).isEqualTo(author);
        assertThat(addedBook.getGenre()).isEqualTo(genre);
        assertThat(addedBook.getYear()).isEqualTo(year);
    }

    @Test
    public void shouldDeleteBook() {
        // given
        long id = 1L;

        List<BookTo> booksBeforeDelete = bookService.findBooks(new BookSearchCriteria());
        assertThat(extractIds(booksBeforeDelete).contains(id)).isTrue();

        // when
        boolean deleted = bookService.deleteBook(id);

        // then
        assertThat(deleted).isTrue();

        List<BookTo> books = bookService.findBooks(new BookSearchCriteria());
        assertThat(extractIds(books).contains(id)).isFalse();
    }

    private void checkBookIds(List<BookTo> books, Set<Long> expectedIds) {
        Set<Long> ids = extractIds(books);
        assertThat(books.size()).isEqualTo(expectedIds.size());
        assertThat(ids).isEqualTo(expectedIds);
    }

    private Set<Long> extractIds(List<BookTo> books) {
        return books.stream().map(BookTo::getId).collect(Collectors.toSet());
    }
}
