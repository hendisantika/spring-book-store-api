package id.my.hendisantika.springbookstoreapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 07:51
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequestScope
@Getter
@Setter
public class RequestMeta {
    private String userName;
    private Long userId;
    private String userType;
    private String emailId;
}