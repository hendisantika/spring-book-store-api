package id.my.hendisantika.springbookstoreapi.service;

import id.my.hendisantika.springbookstoreapi.entity.Book;
import id.my.hendisantika.springbookstoreapi.repository.BookAuthorRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookEditionRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    // Get
    public List<Book> getBooks(Set<Integer> yop, String bookType) {

        List<Book> bookList = new ArrayList<>();

        if (yop == null) {
            bookRepository.findAll()
                    .forEach(book -> bookList.add(book));
        } else {
            return bookRepository.findAllByYearOfPublicationInAndBookType(yop, bookType);
        }

        return bookList;
    }
}
