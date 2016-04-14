package org.itsurvival.books.repository;

import org.itsurvival.books.entity.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Long> {
}
