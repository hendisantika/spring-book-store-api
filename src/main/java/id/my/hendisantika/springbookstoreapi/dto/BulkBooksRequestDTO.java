package id.my.hendisantika.springbookstoreapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 12:37
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class BulkBooksRequestDTO {
    private List<BookDTO> books;
}
