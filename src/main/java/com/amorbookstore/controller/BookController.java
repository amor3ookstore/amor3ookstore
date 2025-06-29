package com.amorbookstore.controller;

import com.amorbookstore.dto.BookDTO;
import com.amorbookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<BookDTO> getBooksByCategory(@PathVariable Long categoryId) {
        return bookService.getBooksByCategory(categoryId);
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
