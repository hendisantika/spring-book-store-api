package id.my.hendisantika.springbookstoreapi.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 07:46
 * To change this template use File | Settings | File Templates.
 */
@Data
public class APIResponse {
    private Integer status;
    private Object data;
    private Object error;

    public APIResponse() {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.error = error;
    }
}
