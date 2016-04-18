package org.itsurvival.books.service.impl;

import java.util.List;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.entity.Book;
import org.itsurvival.books.mapper.BookMapperS;
import org.itsurvival.books.repository.BookRepository;
import org.itsurvival.books.service.BookService;
import org.itsurvival.books.to.BookTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Transactional
@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Autowired
  private BookMapperS bookMapper;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<BookTo> findBooks(BookSearchCriteria bookSearchCriteria) {

    List<Book> books = this.bookRepository.findBooks(bookSearchCriteria);
    return this.bookMapper.booksToBookTos(Lists.newArrayList(books));
  }

  @Override
  public BookTo addBook(BookTo bookTo) {

    return saveBook(bookTo);
  }

  @Override
  public BookTo readBook(long id) {

    Book book = this.bookRepository.findOne(id);
    return this.bookMapper.bookToBookTo(book);
  }

  @Override
  public boolean deleteBook(long id) {

    boolean deleted = false;
    Book book = this.bookRepository.findOne(id);
    if (book != null) {
      this.bookRepository.delete(book);
      deleted = true;
    }
    return deleted;
  }

  private BookTo saveBook(BookTo bookTo) {

    Book book = this.bookMapper.bookToToBook(bookTo);
    Book savedBook = this.bookRepository.saveAndFlush(book);
    return this.bookMapper.bookToBookTo(savedBook);
  }

  @Override
  public BookTo updateBook(BookTo bookTo) {

    return saveBook(bookTo);
  }

}
