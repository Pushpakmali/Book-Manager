package com.acciojob.bookManager;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class BookService {
    private BookRepository bookRepository = new BookRepository();
    public String addBook(Book book) {
        bookRepository.add(book);
        return "Book added with id : "+book.getId();
    }

    public Book getBook(Integer id) throws BookIdInvalidException{
        Optional<Book> bookOptional = bookRepository.getBookById(id);

        if(bookOptional.isEmpty()){
            throw new BookIdInvalidException(id);
        }

        return bookOptional.get();
    }

    public ArrayList<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public Book getBook(String name) throws BookNotFoundException{
        ArrayList<Book> books = bookRepository.getAll();

        for(Book book : books){
            if(book.getTitle().equals(name)){
                return book;
            }
        }

        throw new BookNotFoundException("Book name Invalid");
    }

    public Book updateBookPage(Integer id, Integer pages) throws BookNotFoundException{
        Book book = getBook(id);
        book.setPages(pages);
        bookRepository.add(book);
        return book;
    }

    public void removeBookById(Integer id) {
        bookRepository.deleteBook(id);
    }
}
