package id.my.hendisantika.springbookstoreapi.util;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JwtUtils {

    private static final String secret = "This_is_secret";
    private static final long expiryDuration = 60 * 60;
}
