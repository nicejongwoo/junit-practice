package com.practice.junit.service;

import com.practice.junit.domain.Book;
import com.practice.junit.domain.BookRepository;
import com.practice.junit.util.MailSender;
import com.practice.junit.web.dto.BookResponse;
import com.practice.junit.web.dto.BookSaveRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //mockito 환경 설
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;

    @Test
    void registerBookTest() {
        //given
        BookSaveRequest request = new BookSaveRequest();
        request.setTitle("spring boot");
        request.setAuthor("user");

        //stub
        when(bookRepository.save(any())).thenReturn(request.toEntity());
        when(mailSender.send()).thenReturn(true);

        //when
        BookResponse bookResponse = bookService.registerBook(request);

        //then
        assertThat(bookResponse.getTitle()).isEqualTo(request.getTitle());
        assertThat(bookResponse.getAuthor()).isEqualTo(request.getAuthor());

    }

    @Test
    void listBookTest() {
        //given

        //stub
        List<Book> books = Arrays.asList(
                new Book(1L, "test1", "user1"),
                new Book(2L, "test2", "user2")
        );
        when(bookRepository.findAll()).thenReturn(books);

        //when
        List<BookResponse> bookResponses = bookService.listBook();

        //then
        assertThat(bookResponses.get(0).getTitle()).isEqualTo("test1");
        assertThat(bookResponses.get(1).getTitle()).isEqualTo("test2");
    }

    @Test
    void getOneBookTest() {
        //given
        Long id = 1L;
        Book book = new Book(1L, "test", "user");
        Optional<Book> optionalBook = Optional.of(book);

        //stub
        when(bookRepository.findById(id)).thenReturn(optionalBook);

        //when
        BookResponse response = bookService.getOneBook(id);

        //then
        assertThat(response.getTitle()).isEqualTo(book.getTitle());
        assertThat(response.getAuthor()).isEqualTo(book.getAuthor());
    }

    @Test
    void updateBookTest() {
        //given
        Long id = 1L;

        //stub
        Book book = Book.builder().id(1L).title("test").author("user").build();
        Optional<Book> optionalBook = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(optionalBook);

        //when
        BookSaveRequest request = new BookSaveRequest();
        request.setTitle("update title");
        request.setAuthor("update user");
        BookResponse bookResponse = bookService.updateBook(id, request);

        //then
        assertThat(bookResponse.getTitle()).isEqualTo(request.getTitle());
        assertThat(bookResponse.getAuthor()).isEqualTo(request.getAuthor());
    }

}