package com.practice.junit.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void readyData() {
        String title = "Spring Boot";
        String author = "user";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    @DisplayName("책등록테스트")
    @Test
    void saveTest() {
        //given
        String title = "Spring Boot";
        String author = "user";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        //when
        Book savedBook = bookRepository.save(book);

        //then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isGreaterThan(0);
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
    }

    @DisplayName("책목록 조회 테스트")
    @Test
    void findAllTest() {
        //given

        //when
        List<Book> all = bookRepository.findAll();

        //then
        assertThat(all.size()).isGreaterThan(0);
        assertThat(all.size()).isLessThan(2);
    }

    @DisplayName("책 한권 조회 테스트")
    @Test
    void findByIdTest() {
        //given
        //when
        Book book = bookRepository.findById(1L).get();

        //then
        assertThat(book.getTitle()).isEqualTo("Spring Boot");
        assertThat(book.getAuthor()).isEqualTo("user");
    }

}