package com.amorbookstore.service;

import com.amorbookstore.dto.BookDTO;
import com.amorbookstore.entity.Book;
import com.amorbookstore.repository.BookRepository;
import com.amorbookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    @Test
    void testGetBookById() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookServiceImpl bookService = new BookServiceImpl(bookRepository, null);

        Book book = Book.builder().id(1L).title("Title").author("Author").price(BigDecimal.TEN).stock(5).build();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDTO bookDTO = bookService.getBookById(1L);

        assertEquals("Title", bookDTO.getTitle());
        assertEquals("Author", bookDTO.getAuthor());
    }
}
