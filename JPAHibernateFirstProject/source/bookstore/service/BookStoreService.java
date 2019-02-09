package bookstore.service;

import bookstore.model.Book;

/**
 * Created by kings on 23-Feb-17.
 */
public interface BookStoreService {

    public void persistObjectGraph(Book book);

    public Book retrieveObjectGraph(String isbn);
}
