package jpahibernate.service;

import jpahibernate.model.Book;

/**
 * Created by kings on 23-Feb-17.
 */
public interface BookStoreService {

    public void persistObjectGraph(Book book);

    public Book retrieveObjectGraph(String isbn);
}
