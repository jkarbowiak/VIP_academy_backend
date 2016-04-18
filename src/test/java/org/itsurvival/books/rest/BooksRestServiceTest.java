package org.itsurvival.books.rest;

import org.itsurvival.books.common.BookSearchCriteria;
import org.itsurvival.books.common.Genre;
import org.itsurvival.books.service.BookService;
import org.itsurvival.books.to.BookTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BooksRestServiceTest.BooksRestServiceTestConfiguration.class)
@WebAppConfiguration
public class BooksRestServiceTest {

    @EnableAutoConfiguration
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
    public void shouldReturnBooksWhenFound() throws Exception {
        // given
        when(bookService.findBooks(any(BookSearchCriteria.class))).thenReturn(Arrays.asList(createBook(10L), createBook(20L)));
        // when
        mockMvc.perform(get("/services/books"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].title", is("Test title")))
                .andExpect(jsonPath("$[0].author", is("Test author")))
                .andExpect(jsonPath("$[0].genre", is("CRIME")))
                .andExpect(jsonPath("$[0].year", is(1998)))
                .andExpect(jsonPath("$[0].version", is(2)))
                .andExpect(jsonPath("$[1].id", is(20)));

        verify(bookService).findBooks(bookSearchCriteriaCaptor.capture());
        BookSearchCriteria bookSearchCriteria = bookSearchCriteriaCaptor.getValue();
        assertThat(bookSearchCriteria.getTitle()).isNull();
        assertThat(bookSearchCriteria.getAuthor()).isNull();
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

}
