package org.itsurvival.books.repository.impl;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.entity.Author;
import org.itsurvival.books.entity.Book;
import org.itsurvival.books.entity.PersonalData;
import org.itsurvival.books.repository.BookSearchRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class BookRepositoryImpl implements BookSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = criteriaQuery.from(Book.class);
        CriteriaQuery<Book> select = criteriaQuery.select(from);

        if (StringUtils.hasText(bookSearchCriteria.getTitle())) {
            Expression<String> literal = criteriaBuilder.upper(criteriaBuilder.literal(bookSearchCriteria.getTitle() + "%"));
            Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(from.get(Book.TITLE_PROPERTY)), literal);
            criteriaQuery.where(predicate);
        }

        if (StringUtils.hasText(bookSearchCriteria.getAuthor())) {
            Join<Book, Author> join = from.join(Book.AUTHORS);
            Path<String> authorLastNamePath = join.get(Author.PERSONAL_DATA_PROPERTY).get(PersonalData.LAST_NAME_PARAMETER);
            Expression<String> literal = criteriaBuilder.upper(criteriaBuilder.literal(bookSearchCriteria.getAuthor() + "%"));
            Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(authorLastNamePath), literal);
            criteriaQuery.where(predicate);
        }

        TypedQuery<Book> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();
    }
}
