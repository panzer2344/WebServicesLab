package com.lab.ws.server.service;

import com.lab.ws.LibraryService;
import com.lab.ws.dto.Book;
import com.lab.ws.server.storage.Storage;

import javax.jws.WebService;
import java.math.BigInteger;
import java.util.LinkedList;

@WebService(endpointInterface = "com.lab.ws.LibraryService")
public class LibraryAdminService implements LibraryService {

    private Storage storage = Storage.instance;

    @Override
    public LinkedList<Book> getAllBooks() {
        return new LinkedList<>(storage.getAll());
    }

    @Override
    public void addNewBook(BigInteger code, LinkedList<String> authors, int publishYear, String publisherName, String bookName) {
        Book book = new Book()
                        .setCode(code)
                        .setBookName(bookName)
                        .setAuthors(authors)
                        .setPublishYear(publishYear)
                        .setPublisherName(publisherName);
        storage.addBook(book);
    }

    @Override
    public void addNewCopies(BigInteger code, int count) {
        storage.addBooks(storage.getBook(code), count);
    }

    @Override
    public OperationStatus getBook(BigInteger code) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void returnBook(BigInteger code) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Book getBookDto(BigInteger code) throws UnsupportedOperationException {
        return storage.getBook(code);
    }
}
