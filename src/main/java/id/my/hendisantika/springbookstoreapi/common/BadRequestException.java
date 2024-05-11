package id.my.hendisantika.springbookstoreapi.common;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/11/24
 * Time: 07:47
 * To change this template use File | Settings | File Templates.
 */
public class BadRequestException extends RuntimeException {

    private List<Error> errors;

    public BadRequestException(String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
