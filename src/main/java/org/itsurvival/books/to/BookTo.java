package org.itsurvival.books.to;

public class BookTo {

    private Long id;
    private int version;
    private Genre genre;
    private int year;
    private String title;
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookTo)) return false;

        BookTo bookTo = (BookTo) o;

        if (version != bookTo.version) return false;
        if (year != bookTo.year) return false;
        if (author != null ? !author.equals(bookTo.author) : bookTo.author != null) return false;
        if (genre != bookTo.genre) return false;
        if (id != null ? !id.equals(bookTo.id) : bookTo.id != null) return false;
        if (title != null ? !title.equals(bookTo.title) : bookTo.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + version;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
