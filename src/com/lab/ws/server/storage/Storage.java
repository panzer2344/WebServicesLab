package com.lab.ws.server.storage;

import com.lab.ws.dto.Book;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    public static final Storage instance = new Storage();

    private static final Map<BigInteger, Book> books = new ConcurrentHashMap<>();

    // add test items
    static {
        books.put(
                BigInteger.valueOf(1L),
                new Book()
                    .setCode(BigInteger.valueOf(1L))
                    .setBookName("Harry Potter")
                    .setAuthors(new LinkedList<String>(){{ add("Rowling J."); }})
                    .setPublishYear(1997)
                    .setPublisherName("Machaon")
                    .setInStock(5));
        books.put(
                BigInteger.valueOf(2L),
                new Book()
                        .setCode(BigInteger.valueOf(1L))
                        .setBookName("War and Peace")
                        .setAuthors(new LinkedList<String>(){{ add("Toltoi L.N."); }})
                        .setPublishYear(1883)
                        .setPublisherName("Piter")
                        .setInStock(3));
    }

    public Collection<Book> getAll() {
        return books.values();
    }

    public void addBook(Book book) {
        addBooks(book, 1);
    }

    public void addBooks(Book book, int count) {
        Book inStorage = books.get(book.getCode());

        if(inStorage == null) {
            book = books.put(book.getCode(), book);
        }

        book.setInStock(book.getInStock() + count);
    }

    public Book getBook(BigInteger code) {
        return books.get(code);
    }

    public Book takeBook(BigInteger code) {
        if(books.get(code) != null && books.get(code).getInStock() > 0)
            return books.get(code).setInStock(books.get(code).getInStock() - 1);
        return null;
    }

    public void returnBook(BigInteger code) {
        if(books.get(code) != null && books.get(code).getInStock() > 0)
            books.get(code).setInStock(books.get(code).getInStock() + 1);
    }

}
