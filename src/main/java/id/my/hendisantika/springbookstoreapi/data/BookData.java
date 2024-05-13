package id.my.hendisantika.springbookstoreapi.data;

import id.my.hendisantika.springbookstoreapi.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:40
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class BookData {
    private List<Book> books;
}
