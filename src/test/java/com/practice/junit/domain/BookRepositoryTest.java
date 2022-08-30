package com.practice.junit.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void readyData() {
        String title = "Spring Boot";
        String author = "user";

        book = Book.builder()
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
        // @Transactional 을 통해 초기화가 될때, primary key 의 auto_increment 정보는 초기화가 안됨.
        // 메서드 전체 테스트 실행 시 특정 id 값을 사용하는 테스트의 경우에는 주의 필요

        //given
        //when
        Book foundBook = bookRepository.findById(book.getId()).get();

        //then
        assertThat(foundBook.getTitle()).isEqualTo("Spring Boot");
        assertThat(foundBook.getAuthor()).isEqualTo("user");
    }

    @DisplayName("책 삭제 테스트")
    @Test
    void deleteTest() {
        //given
        //when
        bookRepository.delete(book);
        Optional<Book> optionalBook = bookRepository.findById(book.getId());

        //then
        assertThat(optionalBook).isEmpty();
    }

}