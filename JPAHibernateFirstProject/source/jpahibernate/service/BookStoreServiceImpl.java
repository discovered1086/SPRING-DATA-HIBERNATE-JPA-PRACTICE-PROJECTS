package jpahibernate.service;

import commonutility.ConnectionManager;
import jpahibernate.model.Book;
import jpahibernate.model.Chapter;
import jpahibernate.model.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 23-Feb-17.
 */
public class BookStoreServiceImpl implements BookStoreService {
    ConnectionManager connectionManager = new ConnectionManager();
    Connection connection = null;

    public void persistObjectGraph(Book book) {
        try {

            connection = connectionManager.getMyConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO PUBLISHER (PUBLISHER_CODE, PUBLISHER_NAME) VALUES (?, ?)");
            stmt.setString(1, book.getPublisher().getPublisher_code());
            stmt.setString(2, book.getPublisher().getPublisher_name());
            stmt.executeUpdate();

            stmt.close();

            stmt = connection.prepareStatement("INSERT INTO BOOK (ISBN, BOOK_NAME, PUBLISHER_CODE) VALUES (?, ?, ?)");
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getBookName());
            stmt.setString(3, book.getPublisher().getPublisher_code());
            stmt.executeUpdate();

            stmt.close();

            stmt = connection.prepareStatement("INSERT INTO CHAPTER (BOOK_ISBN, CHAPTER_NUM, TITLE) VALUES (?, ?, ?)");
            for (Chapter chapter : book.getChaptersList()) {
                stmt.setString(1, book.getIsbn());
                stmt.setInt(2, chapter.getChapter_num());
                stmt.setString(3, chapter.getTitle());
                stmt.executeUpdate();
            }

            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Book retrieveObjectGraph(String isbn) {
        Book book = null;
        try {

            connection = connectionManager.getMyConnection();

            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM BOOK, PUBLISHER WHERE BOOK.PUBLISHER_CODE = PUBLISHER.PUBLISHER_CODE AND BOOK.ISBN = ?");
            stmt.setString(1, isbn);

            ResultSet rs = stmt.executeQuery();

            book = new Book();
            if (rs.next()) {
                book.setIsbn(rs.getString("ISBN"));
                book.setBookName(rs.getString("BOOK_NAME"));

                Publisher publisher = new Publisher();
                publisher.setPublisher_code(rs.getString("PUBLISHER_CODE"));
                publisher.setPublisher_name(rs.getString("PUBLISHER_NAME"));
                book.setPublisher(publisher);
            }

            rs.close();
            stmt.close();

            List<Chapter> chapters = new ArrayList<Chapter>();
            stmt = connection.prepareStatement("SELECT * FROM CHAPTER WHERE BOOK_ISBN = ?");
            stmt.setString(1, isbn);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Chapter chapter = new Chapter();
                chapter.setTitle(rs.getString("TITLE"));
                chapter.setChapter_num(rs.getInt("CHAPTER_NUM"));
                chapters.add(chapter);
            }
            book.setChaptersList(chapters);

            connectionManager.closeResultset(rs);
            connectionManager.closePreparedStatement(stmt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return book;
    }
}
