package id.my.hendisantika.springbookstoreapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    private final RequestMeta requestMeta;

    public JwtInterceptor(RequestMeta requestMeta) {
        this.requestMeta = requestMeta;
    }
}
