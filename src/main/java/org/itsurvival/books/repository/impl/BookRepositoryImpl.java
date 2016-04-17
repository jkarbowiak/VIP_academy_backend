package org.itsurvival.books.repository.impl;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.entity.Book;
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

        TypedQuery<Book> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();
    }
}
