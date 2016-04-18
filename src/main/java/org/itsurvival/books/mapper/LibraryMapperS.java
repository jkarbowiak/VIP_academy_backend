package org.itsurvival.books.mapper;

import org.itsurvival.books.entity.Library;
import org.itsurvival.books.to.LibraryTo;
import org.mapstruct.Mapper;

@Mapper(uses = BookMapperS.class)
public interface LibraryMapperS {
  Library libraryToToLibrary(LibraryTo libraryTo);

  LibraryTo libraryToLibraryTo(Library library);

}
