package id.my.hendisantika.springbookstoreapi.repository;

import id.my.hendisantika.springbookstoreapi.dto.BookQueryDslDTO;
import id.my.hendisantika.springbookstoreapi.entity.Book;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public interface BookRepositoryCustom {

    List<Book> getAllBooksByQuerDsl(Integer year);

    List<BookQueryDslDTO> getAllBooksByQuerDslDto(Integer year);

}
