package com.lab.ws;

import com.lab.ws.dto.Book;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.math.BigInteger;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface LibraryService {

    @WebMethod
    List<Book> getAllBooks();

    @WebMethod
    void addNewBook(
            BigInteger code,
            List<String> authors,
            int publishYear,
            String publisherName,
            String bookName);

    @WebMethod
    void addNewCopies(
            BigInteger code,
            int count);

    @WebMethod
    OperationStatus getBook(BigInteger code);

    @WebMethod
    void returnBook(BigInteger code);


    enum OperationStatus {
        OK,
        ERROR
    }
}
