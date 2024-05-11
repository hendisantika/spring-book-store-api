package id.my.hendisantika.springbookstoreapi.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
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

    public static QBook qBook = QBook.book;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getAllBooksByQuerDsl(Integer year) {

        // query dsl
        JPAQuery<Book> jpaQuery = new JPAQuery<>(em);

        // select id, bookType from book where year_of_publication = year;

        // Method 1 : using tuple
       /* List<Tuple> tuples = jpaQuery
                .select(qBook.bookType,qBook.id)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        List<Book> books = new ArrayList<>();

        for(Tuple eachTuple: tuples){
            Book book = new Book();
            book.setId(eachTuple.get(qBook.id));
            book.setBookType(eachTuple.get(qBook.bookType));

            books.add(book);
        }*/

        //Method 2: Using Projection

        QBean<Book> bookQBean = Projections.bean(Book.class,
                qBook.id,
                qBook.bookType
        );

        List<Book> books = jpaQuery
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .select(bookQBean)
                .fetch();

        //return
        return books;
    }
}
