package com.practice.junit.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("책등록테스트")
    @Test
    void save_test() {
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

}