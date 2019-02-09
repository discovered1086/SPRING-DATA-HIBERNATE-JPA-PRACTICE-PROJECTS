package jpahibernate.clients;

import jpahibernate.model.Book;
import jpahibernate.service.BookStoreService;
import jpahibernate.service.BookStoreServiceImpl;

/**
 * Created by kings on 24-Feb-17.
 */
public class BookStoreClient{
    BookStoreService bookStoreService = null;
    public static void main(String[] args) {
          new BookStoreClient().CallBookStoreService();
    }

    public void CallBookStoreService(){
        bookStoreService=getBookStoreService();
        //persisting object graph
        /*Publisher publisher = new Publisher("MANN", "Manning Publications Co.");

        Book book = new Book("9781617290459", "Java Persistence with Hibernate, Second Edition", publisher);

        List<Chapter> chapters = new ArrayList<Chapter>();
        Chapter chapter1 = new Chapter(1,"Introducing JPA and Hibernate");
        chapters.add(chapter1);
        Chapter chapter2 = new Chapter(2,"Domain Models and Metadata");
        chapters.add(chapter2);

        book.setChaptersList(chapters);

        bookStoreService.persistObjectGraph(book);*/

        //retrieving object graph

		Book book = bookStoreService.retrieveObjectGraph("9781617290459");
		System.out.println(book);

    }

    public BookStoreService getBookStoreService() {
        return new BookStoreServiceImpl();
    }
}



