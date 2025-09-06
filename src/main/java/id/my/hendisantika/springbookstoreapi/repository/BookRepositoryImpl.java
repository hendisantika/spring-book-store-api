package id.my.hendisantika.springbookstoreapi.repository;

// import com.querydsl.core.types.Projections;
// import com.querydsl.core.types.QBean;
// import com.querydsl.jpa.impl.JPAQuery;
import id.my.hendisantika.springbookstoreapi.dto.BookQueryDslDTO;
import id.my.hendisantika.springbookstoreapi.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

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

    // public static QBook qBook = QBook.book;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getAllBooksByQuerDsl(Integer year) {
        // TODO: Implement QueryDSL after Q classes are generated
        return null;
    }

    @Override
    public List<BookQueryDslDTO> getAllBooksByQuerDslDto(Integer year) {
        // TODO: Implement QueryDSL after Q classes are generated
        return null;
    }
}
