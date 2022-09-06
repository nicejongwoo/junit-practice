package com.practice.junit.service;

import com.practice.junit.domain.Book;
import com.practice.junit.domain.BookRepository;
import com.practice.junit.util.MailSender;
import com.practice.junit.web.dto.BookResponse;
import com.practice.junit.web.dto.BookSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    //책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponse registerBook(BookSaveRequest request) {
        Book book = bookRepository.save(request.toEntity());
        if (book != null) {
            if (!mailSender.send()) {
                throw new RuntimeException("메일전송 실패");
            }
        }
        return book.toDto();
    }

    //책목록
    public List<BookResponse> listBook() {
        return bookRepository.findAll()
                .stream()
                .map(Book::toDto)
                .collect(Collectors.toList());
    }

    //책상세보기
    public BookResponse getOneBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get().toDto();
        } else {
            throw new RuntimeException("Not Found Book");
        }
    }

    //책삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    //책수정
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateBook(Long id, BookSaveRequest request) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().update(request.getTitle(), request.getAuthor());
        } else {
            throw new RuntimeException("Not Found Book");
        }
    }

}
