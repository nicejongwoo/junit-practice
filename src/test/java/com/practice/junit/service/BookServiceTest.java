package com.practice.junit.service;

import com.practice.junit.domain.BookRepository;
import com.practice.junit.util.MailSender;
import com.practice.junit.web.dto.BookResponse;
import com.practice.junit.web.dto.BookSaveRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        assertThat(request.getTitle()).isEqualTo(bookResponse.getTitle());
        assertThat(request.getAuthor()).isEqualTo(bookResponse.getTitle());

    }

}