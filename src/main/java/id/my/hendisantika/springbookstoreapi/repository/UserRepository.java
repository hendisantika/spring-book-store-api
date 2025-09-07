package id.my.hendisantika.springbookstoreapi.repository;

import id.my.hendisantika.springbookstoreapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

    User findOneByEmailIdIgnoreCase(String emailId);
}
