package com.practice.junit.domain;

import com.practice.junit.web.dto.BookResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String author;

    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookResponse toDto() {
        return BookResponse.builder()
                .id(id)
                .title(title)
                .author(author)
                .build();
    }
}
