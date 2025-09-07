package id.my.hendisantika.springbookstoreapi.repository;

import id.my.hendisantika.springbookstoreapi.entity.BookEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface BookEditionRepository extends JpaRepository<BookEdition, Long> {

    List<BookEdition> findByBookId(Long bookId);
}
