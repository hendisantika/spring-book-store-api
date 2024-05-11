package id.my.hendisantika.springbookstoreapi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public class BookRepositoryImpl implements BookRepositoryCustom {

    public static QBook qBook = QBook.book;
    @PersistenceContext
    private EntityManager em;
}
