package org.itsurvival.books.service.impl;

import com.google.common.collect.Lists;
import org.itsurvival.books.entity.Book;
import org.itsurvival.books.mapper.BookMapper;
import org.itsurvival.books.repository.BookRepository;
import org.itsurvival.books.service.BookService;
import org.itsurvival.books.to.BookTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookTo> findBooks() {
        Iterable<Book> books = bookRepository.findAll();
        return bookMapper.mapSourceCollection(Lists.newArrayList(books));
    }

    @Override
    public BookTo addBook(BookTo bookTo) {
        return saveBook(bookTo);
    }

    @Override
    public BookTo updateBook(BookTo bookTo) {
        return saveBook(bookTo);
    }

    @Override
    public boolean deleteBook(Long id) {
        boolean deleted = false;
        Book book = bookRepository.findOne(id);
        if (book != null) {
            bookRepository.delete(book);
            deleted = true;
        }
        return deleted;
    }

    private BookTo saveBook(BookTo bookTo) {
        Book book = bookMapper.convertToEntity(bookTo);
        Book savedBook = bookRepository.save(book);
        return bookMapper.convertToTransportObject(savedBook);
    }
}
