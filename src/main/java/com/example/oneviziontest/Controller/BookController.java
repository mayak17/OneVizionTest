package com.example.oneviziontest.Controller;

import com.example.oneviziontest.Entity.Book;
import com.example.oneviziontest.Service.BookService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> allBook(){
        List<Book> allBook = this.bookService.findAllBook();
        return ResponseEntity.ok(allBook);
    }

    @GetMapping("/books/reverseTitle")
    public ResponseEntity<List<Book>> allBookSortAndReverseByTitle() {
        List<Book> sortedReverseBook = this.bookService.findAllBook()
                        .stream()
                        .sorted(Comparator.comparing(Book::getTitle))
                        .collect(Collectors.toList());

        Collections.reverse(sortedReverseBook);

        return ResponseEntity.ok(sortedReverseBook);
    }

    @GetMapping("/books/orderAuthor")
    public ResponseEntity<List<Book>> allBookOrderByAuthor(){
        List<Book> orderAuthorBook = this.bookService.findAllBookOrderByAuthor();
        return ResponseEntity.ok(orderAuthorBook);
    }


    @PostMapping("/save-book")
    public ResponseEntity saveBook(@RequestBody Book book){
        try {
            this.bookService.saveOneBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (HibernateException e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
}
