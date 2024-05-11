package id.my.hendisantika.springbookstoreapi.repository;

import id.my.hendisantika.springbookstoreapi.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long>, BookRepositoryCustom {
    String rawQuery = "select * from book where year_of_publication IN ?1";

    List<Book> findAllByYearOfPublicationInAndBookType(Set<Integer> yop, String bookType);

    Long countByBookType(String bookType);

    @Query(nativeQuery = true, value = rawQuery)
    List<Book> findAllByYearOfPublicationIn(Set<Integer> yop);
}
