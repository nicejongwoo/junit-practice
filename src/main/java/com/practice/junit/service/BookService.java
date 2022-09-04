package com.practice.junit.service;

import com.practice.junit.domain.Book;
import com.practice.junit.domain.BookRepository;
import com.practice.junit.web.dto.BookResponse;
import com.practice.junit.web.dto.BookSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    //책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponse registerBook(BookSaveRequest request) {
        Book book = bookRepository.save(request.toEntity());
        return new BookResponse().toDto(book);
    }

    //책목록
    public List<BookResponse> listBook() {
        return bookRepository.findAll()
                .stream()
                .map(new BookResponse()::toDto)
                .collect(Collectors.toList());
    }

    //책상세보기
    //책삭제
    //책수정

}
