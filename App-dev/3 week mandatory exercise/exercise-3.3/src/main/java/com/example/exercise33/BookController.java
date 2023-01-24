package com.example.exercise33;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;



/**
 * BookController
 * @author Majd
 * @version 2023-01-24
 */
@RestController
public class BookController {
    private List<Book> books;

    public BookController() {
        initializeData();
    }

    private void initializeData() {
        Author author1 = new Author(1, "Stephen", "King", 1947);
        Author author2 = new Author(2, "J.K.", "Rowling", 1965);
        Author author3 = new Author(3, "George", "Orwell", 1903);
        books = Arrays.asList(
                new Book(1, "The Shining", 1977, 447),
                new Book(2, "Harry Potter and the Philosopher's Stone", 1997, 223),
                new Book(3, "1984", 1949, 328)
        );
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        //validate the book object
        if(book.title() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Add the book to the collection
        books.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        //a while loop here is best of course, but this is just for now...
        for(Book book: books) {
            if (book.id() == id) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
