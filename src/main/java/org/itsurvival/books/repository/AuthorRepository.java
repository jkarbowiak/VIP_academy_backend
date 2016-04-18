package org.itsurvival.books.repository;

import org.itsurvival.books.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select author from Author author where author.personalData.lastName = :lastName")
    Author findByLastName(@Param("lastName")String lastName);
}
