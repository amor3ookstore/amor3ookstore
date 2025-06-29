package com.amorbookstore.config;

import com.amorbookstore.entity.Book;
import com.amorbookstore.entity.Category;
import com.amorbookstore.repository.BookRepository;
import com.amorbookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            Category fiction = Category.builder().name("Fiction").build();
            Category science = Category.builder().name("Science").build();
            categoryRepository.save(fiction);
            categoryRepository.save(science);

            Book book1 = Book.builder().title("The Great Gatsby").author("F. Scott Fitzgerald").price(BigDecimal.valueOf(10.99)).stock(100).category(fiction).build();
            Book book2 = Book.builder().title("A Brief History of Time").author("Stephen Hawking").price(BigDecimal.valueOf(15.99)).stock(50).category(science).build();

            bookRepository.save(book1);
            bookRepository.save(book2);
        }
    }
}
