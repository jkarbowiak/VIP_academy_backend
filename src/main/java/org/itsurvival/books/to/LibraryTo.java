package org.itsurvival.books.to;

import java.util.HashSet;
import java.util.Set;

public class LibraryTo {
  private Long id;

  private String name;

  private Set<BookTo> books = new HashSet<>();

  public Long getId() {

    return this.id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return this.name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public Set<BookTo> getBooks() {

    return this.books;
  }

  public void setBooks(Set<BookTo> books) {

    this.books = books;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (this.id ^ (this.id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    LibraryTo other = (LibraryTo) obj;
    if (this.id != other.id)
      return false;
    return true;
  }

}
