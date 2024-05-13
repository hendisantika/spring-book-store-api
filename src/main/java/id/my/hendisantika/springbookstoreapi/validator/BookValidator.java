package id.my.hendisantika.springbookstoreapi.validator;

import id.my.hendisantika.springbookstoreapi.common.Error;
import id.my.hendisantika.springbookstoreapi.dto.BookRequestDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:38
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BookValidator {
    public List<Error> validateCreateBookRequest(BookRequestDTO bookDTO) {

        List<Error> errors = new ArrayList<>();

        // name
        if (bookDTO.getName() == null) {
            Error error = new Error("name", "book name is null");
            errors.add(error);
        }

        // yop
        if (bookDTO.getYearOfPublication() == null) {
            Error error = new Error("yop", "yop is null");
            errors.add(error);
        }

        // book type
        if (bookDTO.getBookType() == null) {
            errors.add(new Error("bookType", "bookType is null"));
        }

        return errors;
    }
}
