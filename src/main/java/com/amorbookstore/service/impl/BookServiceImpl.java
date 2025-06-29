package com.amorbookstore.service.impl;

import com.amorbookstore.dto.BookDTO;
import com.amorbookstore.entity.Book;
import com.amorbookstore.entity.Category;
import com.amorbookstore.exception.ResourceNotFoundException;
import com.amorbookstore.repository.BookRepository;
import com.amorbookstore.repository.CategoryRepository;
import com.amorbookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    private BookDTO mapToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stock(book.getStock())
                .description(book.getDescription())
                .imageUrl(book.getImageUrl())
                .categoryId(book.getCategory() != null ? book.getCategory().getId() : null)
                .categoryName(book.getCategory() != null ? book.getCategory().getName() : null)
                .build();
    }

    private Book mapToEntity(BookDTO dto) {
        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        }
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return mapToDTO(book);
    }

    @Override
    public List<BookDTO> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = mapToEntity(bookDTO);
        Book saved = bookRepository.save(book);
        return mapToDTO(saved);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPrice(bookDTO.getPrice());
        book.setStock(bookDTO.getStock());
        book.setDescription(bookDTO.getDescription());
        book.setImageUrl(bookDTO.getImageUrl());
        if (bookDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(bookDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            book.setCategory(category);
        }
        Book updated = bookRepository.save(book);
        return mapToDTO(updated);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
