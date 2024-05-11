package id.my.hendisantika.springbookstoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String gender;
}