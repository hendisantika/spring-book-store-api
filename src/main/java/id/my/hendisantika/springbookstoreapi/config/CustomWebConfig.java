package id.my.hendisantika.springbookstoreapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

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

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //sort
        SortHandlerMethodArgumentResolver sortResolver = new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("order-by");

        PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver(sortResolver);

        pageResolver.setPageParameterName("page-number");
        pageResolver.setSizeParameterName("page-size");
        pageResolver.setOneIndexedParameters(true);

        Pageable defaultPageable = PageRequest.of(0, 5);
        pageResolver.setFallbackPageable(defaultPageable);

        argumentResolvers.add(pageResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor);
    }

    @Bean
    @RequestScope
    public RequestMeta getRequestMeta() {
        return new RequestMeta();
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor(getRequestMeta());
    }
}
