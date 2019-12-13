package com.lab.ws;

import com.lab.ws.dto.Book;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.math.BigInteger;
import java.util.LinkedList;

@WebService
@SOAPBinding(use = SOAPBinding.Use.ENCODED)
public interface LibraryService {

    @WebMethod
    LinkedList<Book> getAllBooks() throws UnsupportedOperationException;

    @WebMethod
    void addNewBook(
            BigInteger code,
            LinkedList<String> authors,
            int publishYear,
            String publisherName,
            String bookName) throws UnsupportedOperationException;

    @WebMethod
    void addNewCopies(
            BigInteger code,
            int count) throws UnsupportedOperationException;

    @WebMethod
    OperationStatus getBook(BigInteger code) throws UnsupportedOperationException;

    @WebMethod
    void returnBook(BigInteger code) throws UnsupportedOperationException;

    @WebMethod
    Book getBookDto(BigInteger code) throws UnsupportedOperationException;

    enum OperationStatus {
        OK,
        ERROR
    }
}
