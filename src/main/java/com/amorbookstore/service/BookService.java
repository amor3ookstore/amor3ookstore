package com.amorbookstore.service;

import com.amorbookstore.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    List<BookDTO> getBooksByCategory(Long categoryId);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
}
