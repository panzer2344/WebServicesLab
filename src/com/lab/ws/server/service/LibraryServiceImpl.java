package com.lab.ws.server.service;

import com.lab.ws.LibraryService;
import com.lab.ws.dto.Book;
import com.lab.ws.server.storage.Storage;

import javax.jws.WebService;
import java.math.BigInteger;
import java.util.List;

@WebService(endpointInterface = "com.lab.ws.LibraryService")
public class LibraryServiceImpl implements LibraryService {

    private Storage storage;

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public void addNewBook(BigInteger code, List<String> authors, int publishYear, String publisherName, String bookName) {

    }

    @Override
    public void addNewCopies(BigInteger code, int count) {

    }

    @Override
    public OperationStatus getBook(BigInteger code) {
        return null;
    }

    @Override
    public void returnBook(BigInteger code) {

    }
}
