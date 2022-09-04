package com.practice.junit.web.dto;

import com.practice.junit.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSaveRequest {

    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }

}
