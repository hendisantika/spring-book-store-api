package id.my.hendisantika.springbookstoreapi.repository;

import id.my.hendisantika.springbookstoreapi.entity.BookAuthor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 12:43
 * To change this template use File | Settings | File Templates.
 */
public interface BookAuthorRepository extends CrudRepository<BookAuthor, Long> {

    List<BookAuthor> findAllByBookId(Long bookId);
}
