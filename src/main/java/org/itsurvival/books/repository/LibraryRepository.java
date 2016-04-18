package org.itsurvival.books.repository;

import org.itsurvival.books.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
