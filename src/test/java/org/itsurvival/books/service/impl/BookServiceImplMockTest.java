package org.itsurvival.books.service.impl;

import com.google.common.collect.Lists;
import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.entity.Book;
import org.itsurvival.books.mapper.BookMapperS;
import org.itsurvival.books.repository.BookRepository;
import org.itsurvival.books.to.BookTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceImplMockTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookMapperS bookMapper;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindBooks() {
        // given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();

        List<Book> books = Lists.newArrayList(new Book(), new Book());
        when(bookRepository.findBooks(bookSearchCriteria)).thenReturn(books);

        List<BookTo> bookTos = Lists.newArrayList(new BookTo(), new BookTo());
        when(bookMapper.booksToBookTos(books)).thenReturn(bookTos);

        // when
        List<BookTo> results = bookService.findBooks(bookSearchCriteria);

        // then
        assertThat(results).isSameAs(bookTos);
        verify(bookRepository).findBooks(bookSearchCriteria);
        verify(bookMapper).booksToBookTos(books);
    }
}
