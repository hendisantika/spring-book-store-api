package id.my.hendisantika.springbookstoreapi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 12:38
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class SignUpRequestDTO {
    private String name;
    private String gender;
    private String emailId;
    private String phoneNumber;
    private String password;
}
