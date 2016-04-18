package org.itsurvival.books.mapper;

import java.util.HashSet;

import org.itsurvival.books.entity.Library;
import org.itsurvival.books.to.LibraryTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryMapper extends AbstractMapper<Library, LibraryTo> {

  private BookMapper bookMapper;

  @Autowired
  public LibraryMapper(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }

  @Override
  public LibraryTo convertToTransportObject(Library library) {

    LibraryTo libraryTo = null;
    if (library != null) {
      libraryTo = new LibraryTo();
      libraryTo.setId(library.getId());
      libraryTo.setName(library.getName());
      libraryTo.setBooks(new HashSet<>(this.bookMapper.mapSourceCollection(library.getBooks())));
    }
    return libraryTo;
  }

  @Override
  public Library convertToEntity(LibraryTo libraryTo) {

    Library library = null;
    if (libraryTo != null) {
      library = new Library();
      library.setBooks(new HashSet<>(this.bookMapper.mapTargetCollection(libraryTo.getBooks())));
      library.setName(libraryTo.getName());
    }
    return library;
  }

}
