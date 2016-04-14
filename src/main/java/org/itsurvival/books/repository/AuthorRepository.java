package org.itsurvival.books.repository;

import org.itsurvival.books.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
