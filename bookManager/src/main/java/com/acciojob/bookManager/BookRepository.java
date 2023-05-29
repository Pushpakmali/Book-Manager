package com.acciojob.bookManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookRepository {
    private Map<Integer, Book> bookData = new HashMap<>();

    public void add(Book book) {
        bookData.put(book.getId(), book);
    }

    public Optional<Book> getBookById(Integer id) {
        if(bookData.containsKey(id))return Optional.of(bookData.get(id));

        return Optional.empty();
    }

    public ArrayList<Book> getAll() {
        return new ArrayList<>(bookData.values());
    }

    public void deleteBook(Integer id) {
        bookData.remove(id);
    }
}
