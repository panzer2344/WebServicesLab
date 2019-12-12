package com.lab.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlElement private BigInteger code;
    @XmlElement private List<String> authors;
    @XmlElement private int publishYear;
    @XmlElement private String publisherName;
    @XmlElement private String bookName;
    /* count of books, that is allowed to use for readers; count in stock  */
    @XmlElement private int inStock;

    public Book() {}

    public BigInteger getCode() {
        return code;
    }

    public Book setCode(BigInteger code) {
        this.code = code;
        return this;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public Book setAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public Book setPublishYear(int publishYear) {
        this.publishYear = publishYear;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public int getInStock() {
        return inStock;
    }

    public Book setInStock(int inStock) {
        this.inStock = inStock;
        return this;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public Book setPublisherName(String publisherName) {
        this.publisherName = publisherName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getPublishYear() == book.getPublishYear() &&
                getInStock() == book.getInStock() &&
                Objects.equals(getCode(), book.getCode()) &&
                Objects.equals(getAuthors(), book.getAuthors()) &&
                Objects.equals(publisherName, book.publisherName) &&
                Objects.equals(getBookName(), book.getBookName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getAuthors(), getPublishYear(), publisherName, getBookName(), getInStock());
    }

    @Override
    public String toString() {
        return "Book{" +
                "code='" + code + '\'' +
                ", authors=" + authors +
                ", publishYear=" + publishYear +
                ", publisherName='" + publisherName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}
