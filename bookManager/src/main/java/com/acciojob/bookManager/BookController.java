package com.acciojob.bookManager;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController            // This is for telling spring-boot that this is my controller class;
public class BookController {

    private BookService bookService = new BookService();
    @PostMapping("/add-new-book")                //PostMapping is used to connect this url to this method;
    public ResponseEntity<String> addBook(@RequestBody Book book){  //RequestBody is for sending an object in API calls;
//        bookData.put(book.getId(), book);
        String str = bookService.addBook(book);
        return  new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @GetMapping("/get-book")
    public ResponseEntity<?> getBook(@RequestParam Integer id){          //localhost:8080/get-book?id=1
        try{
            Book book = bookService.getBook(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        catch(BookIdInvalidException b){
            System.out.println("Did not find Book");
            return new ResponseEntity<>("Book with Id not found : "+id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<ArrayList<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/get-book-by-name/{name}")                     //localhost:8080/get-book-by-name/{book name}
    public ResponseEntity<?> getBookByName(@PathVariable String name){
        try {
            return new ResponseEntity<>(bookService.getBook(name), HttpStatus.OK);
        }
        catch(BookNotFoundException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-book")
    public ResponseEntity<Book> updateBook(@RequestParam Integer id,@RequestParam Integer pages){
        try {
            Book book = bookService.updateBookPage(id, pages);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }
        catch(BookNotFoundException ex){
            return new ResponseEntity<>(null,  HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        bookService.removeBookById(id);
        return new ResponseEntity<>("Book deleted with id : "+id, HttpStatus.OK);
    }
}
