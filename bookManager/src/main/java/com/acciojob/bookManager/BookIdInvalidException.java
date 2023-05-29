package com.acciojob.bookManager;

public class BookIdInvalidException extends RuntimeException{
    public BookIdInvalidException(Integer id){
        super("Book Not Found With Id : "+id);
    }
}
