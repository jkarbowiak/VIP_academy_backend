package org.itsurvival.books.service.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

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

import com.google.common.collect.Lists;

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
    when(this.bookRepository.findBooks(bookSearchCriteria)).thenReturn(books);

    List<BookTo> bookTos = Lists.newArrayList(new BookTo(), new BookTo());
    when(this.bookMapper.booksToBookTos(books)).thenReturn(bookTos);

    // when
    List<BookTo> results = this.bookService.findBooks(bookSearchCriteria);

    // then
    assertThat(results).isSameAs(bookTos);
    verify(this.bookRepository).findBooks(bookSearchCriteria);
    verify(this.bookMapper).booksToBookTos(books);
  }
}
