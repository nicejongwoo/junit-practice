package com.practice.junit.web.dto;

import com.practice.junit.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResponse {
    private Long id;
    private String title;
    private String author;

    public BookResponse toDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();

        return this;
    }
}
