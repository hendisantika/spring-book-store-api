package id.my.hendisantika.springbookstoreapi.service;

import id.my.hendisantika.springbookstoreapi.common.BadRequestException;
import id.my.hendisantika.springbookstoreapi.dto.BookRequestDTO;
import id.my.hendisantika.springbookstoreapi.entity.Book;
import id.my.hendisantika.springbookstoreapi.entity.BookEdition;
import id.my.hendisantika.springbookstoreapi.repository.BookAuthorRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookEditionRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    // Create
    public Book createBook(BookRequestDTO bookDTO) {

        // validation
        List<Error> errors = bookValidator.validateCreateBookRequest(bookDTO);

        // if not success
        if (errors.size() > 0) {
            throw new BadRequestException("bad request", errors);
        }

        // if success
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setBookType(bookDTO.getBookType());
        book.setDesc(bookDTO.getDesc());
        book.setYearOfPublication(bookDTO.getYearOfPublication());
        bookRepository.save(book);

        // populate edition
        if (!Objects.isNull(bookDTO.getEditions())) {
            bookDTO.getEditions().forEach(bookEditionDTO -> {
                BookEdition bookEdition = new BookEdition();
                bookEdition.setBook(book);
                bookEdition.setIsbn(bookEditionDTO.getIsbn());
                bookEdition.setDescription(bookEditionDTO.getDesc());
                bookEdition.setPageSize(bookEditionDTO.getPageSize());
                bookEdition.setPrice(bookEditionDTO.getPrice());
                bookEditionRepository.save(bookEdition);
            });
        }
        return book;
    }
}
