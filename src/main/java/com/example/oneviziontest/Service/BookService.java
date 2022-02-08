package com.example.oneviziontest.Service;

import com.example.oneviziontest.DAO.BookDAO;
import com.example.oneviziontest.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> findAllBook(){
        return this.bookDAO.getAllBook();
    }

    public void saveOneBook(Book book){
        this.bookDAO.saveBook(book);
    }

    public List<Book> findAllBookOrderByAuthor(){
        return this.bookDAO.getAllBookOrderByAuthor();
    }

}
