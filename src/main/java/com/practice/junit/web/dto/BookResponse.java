package com.practice.junit.web.dto;

import com.practice.junit.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResponse {
    private Long id;
    private String title;
    private String author;

    @Builder
    public BookResponse(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
