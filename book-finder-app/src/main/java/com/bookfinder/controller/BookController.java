package com.bookfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookfinder.model.Book;
import com.bookfinder.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // ✅ Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ✅ Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            Book book = bookRepository.findById(id).get();
            return ResponseEntity.ok(book);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Book not found");
            error.put("id", id);
            return ResponseEntity.status(404).body(error);
        }
    }

    // ✅ Search by title (case-insensitive)
    @GetMapping("/search")
    public ResponseEntity<?> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        if (books.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "No books found matching title: " + title);
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok(books);
    }
}
