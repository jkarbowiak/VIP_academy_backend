package org.itsurvival.books.mapper;

import org.itsurvival.books.entity.Author;
import org.itsurvival.books.to.AuthorTo;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorMapperS {

  Author authorToToAuthor(AuthorTo authorTo);

  AuthorTo authorToAuthorTo(Author author);

}
