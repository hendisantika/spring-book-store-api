package id.my.hendisantika.springbookstoreapi.service;

import id.my.hendisantika.springbookstoreapi.repository.BookAuthorRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookEditionRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:32
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookAuthorRepository bookAuthorRepository;

    private final BookValidator bookValidator;
    private final BookEditionRepository bookEditionRepository;

}
