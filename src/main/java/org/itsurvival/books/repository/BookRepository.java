package org.itsurvival.books.repository;

import org.itsurvival.books.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
