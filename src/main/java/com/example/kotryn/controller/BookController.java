package com.example.kotryn.controller;

import com.example.kotryn.entity.Book;
import com.example.kotryn.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> findAllUsers() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBook(@RequestBody Book addBookRequest) {
        Book book = new Book();
        book.setAuthorName(addBookRequest.getAuthorName());
        book.setBookName(addBookRequest.getBookName());
        bookRepository.save(book);
    }
}
