package id.my.hendisantika.springbookstoreapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 07:50
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class CustomWebConfig {
    @Autowired
    private JwtInterceptor jwtInterceptor;

}
