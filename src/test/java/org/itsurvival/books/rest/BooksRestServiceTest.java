package org.itsurvival.books.rest;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.common.Genre;
import org.itsurvival.books.service.BookService;
import org.itsurvival.books.to.BookTo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BooksRestServiceTest.BooksRestServiceTestConfiguration.class)
@WebAppConfiguration
public class BooksRestServiceTest {

    @EnableWebMvc
    @ComponentScan("org.itsurvival.books.rest")
    public static class BooksRestServiceTestConfiguration {

        @Bean
        public BookService bookService() {
            return Mockito.mock(BookService.class);
        }
    }

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BookService bookService;

    @Captor
    private ArgumentCaptor<BookSearchCriteria> bookSearchCriteriaCaptor;

    @Captor
    private ArgumentCaptor<BookTo> bookCaptor;

    @Before
    public void setUp() {
        reset(bookService);
        initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnEmptyArrayWhenNoResultsFound() throws Exception {
        // given
        when(bookService.findBooks(any(BookSearchCriteria.class))).thenReturn(Arrays.asList());
        // when
        mockMvc.perform(get("/services/books"))
                // then
                .andExpect(status().isOk());

        verify(bookService).findBooks(bookSearchCriteriaCaptor.capture());
        BookSearchCriteria bookSearchCriteria = bookSearchCriteriaCaptor.getValue();
        assertThat(bookSearchCriteria.getTitle()).isNull();
        assertThat(bookSearchCriteria.getAuthor()).isNull();
    }

    @Test
    @Ignore("not implemented")
    public void shouldReturnBooksWhenFound() throws Exception {
        // TODO
    }

    @Test
    public void shouldAddNewBook() throws Exception {

        // given
        byte[] content = readFileToBytes("classpath:org/itsurvival/books/rest/newBook.json");

        when(bookService.addBook(any(BookTo.class))).thenAnswer(args -> args.getArguments()[0]);

        // when
        mockMvc.perform(post("/services/book").content(content).contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test title")));


        verify(bookService).addBook(bookCaptor.capture());
        BookTo book = bookCaptor.getValue();
        assertThat(book.getTitle()).isEqualTo("Test title");
        assertThat(book.getAuthor()).isEqualTo("Test author");
        assertThat(book.getYear()).isEqualTo(2008);
        assertThat(book.getGenre()).isEqualTo(Genre.IT);
        assertThat(book.getVersion()).isEqualTo(0L);
        assertThat(book.getId()).isNull();
    }

    @Test
    @Ignore("not implemented")
    public void shouldUpdateExistingBook() throws Exception {
        // TODO
    }

    @Test
    @Ignore("not implemented")
    public void shouldDeleteBook() throws Exception {
        // TODO
    }

    private BookTo createBook(Long id) {
        BookTo bookTo = new BookTo();
        bookTo.setAuthor("Test author");
        bookTo.setGenre(Genre.CRIME);
        bookTo.setTitle("Test title");
        bookTo.setYear(1998);
        bookTo.setId(id);
        bookTo.setVersion(2L);
        return bookTo;
    }

    private byte[] readFileToBytes(String resourcePath) throws IOException {
        return Files.readAllBytes(webApplicationContext.getResource(resourcePath).getFile().toPath());
    }

}
