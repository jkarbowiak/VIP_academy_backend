package org.itsurvival.books.repository;

import org.itsurvival.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookSearchRepository {

}
