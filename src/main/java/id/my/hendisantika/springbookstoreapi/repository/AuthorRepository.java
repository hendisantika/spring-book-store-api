package id.my.hendisantika.springbookstoreapi.repository;

import id.my.hendisantika.springbookstoreapi.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findAllByOrderByIdDesc(Pageable pageable);
}
