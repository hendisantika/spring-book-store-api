package id.my.hendisantika.springbookstoreapi.service;

import id.my.hendisantika.springbookstoreapi.common.APIResponse;
import id.my.hendisantika.springbookstoreapi.common.BadRequestException;
import id.my.hendisantika.springbookstoreapi.data.BookData;
import id.my.hendisantika.springbookstoreapi.dto.AuthorDTO;
import id.my.hendisantika.springbookstoreapi.dto.BookDTO;
import id.my.hendisantika.springbookstoreapi.dto.BookQueryDslDTO;
import id.my.hendisantika.springbookstoreapi.dto.BookRequestDTO;
import id.my.hendisantika.springbookstoreapi.dto.BulkBooksRequestDTO;
import id.my.hendisantika.springbookstoreapi.entity.Author;
import id.my.hendisantika.springbookstoreapi.entity.Book;
import id.my.hendisantika.springbookstoreapi.entity.BookAuthor;
import id.my.hendisantika.springbookstoreapi.entity.BookEdition;
import id.my.hendisantika.springbookstoreapi.repository.BookAuthorRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookEditionRepository;
import id.my.hendisantika.springbookstoreapi.repository.BookRepository;
import id.my.hendisantika.springbookstoreapi.validator.BookValidator;
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
        List<id.my.hendisantika.springbookstoreapi.common.Error> errors = bookValidator.validateCreateBookRequest(bookDTO);

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

    // Single resource
    public BookDTO getBookById(Long bookId, boolean authorData) {

        Book book;
        List<BookAuthor> bookAuthors = null;

        book = bookRepository.findById(bookId).get();

        if (authorData) {
            bookAuthors = bookAuthorRepository.findAllByBookId(bookId);
        }

        BookDTO bookDTO = new BookDTO();

        // set book details
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setDesc(book.getDesc());
        bookDTO.setYearOfPublication(book.getYearOfPublication());
        bookDTO.setBookType(book.getBookType());

        // get author details
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        if (bookAuthors != null) {
            for (BookAuthor bookAuthor : bookAuthors) {
                Author author = bookAuthor.getAuthor();

                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(author.getId());
                authorDTO.setName(author.getName());
                authorDTO.setGender(author.getGender());

                authorDTOList.add(authorDTO);
            }

            // set author details
            bookDTO.setAuthors(authorDTOList);
        }
        return bookDTO;
    }

    // Update
    public Book updateBook(Book incomingBook) {
        return bookRepository.save(incomingBook);
    }

    // Delete
    public String deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
        return "Deleted Successfully";
    }

    // raw query - get books
    public APIResponse getBooksByRawQuery(Set<Integer> yop) {
        APIResponse apiResponse = new APIResponse();

        // db call
        List<Book> bookList = bookRepository.findAllByYearOfPublicationIn(yop);

        // set data
        BookData bookData = new BookData();
        bookData.setBooks(bookList);

        // set api response
        apiResponse.setData(bookData);

        return apiResponse;
    }

    public APIResponse getCaughtException(Integer yop) {
        int result = 100 / yop;

        APIResponse response = new APIResponse();
        response.setData(result);

        return response;
    }

    public APIResponse getBooksByQueryDsl(Integer year) {
        APIResponse apiResponse = new APIResponse();

        // repo to get the result
        // List<Book> bookList = bookRepository.getAllBooksByQuerDsl(year);

        List<BookQueryDslDTO> bookQueryDslDTOS = bookRepository.getAllBooksByQuerDslDto(year);

        apiResponse.setData(bookQueryDslDTOS);

        //return
        return apiResponse;
    }

    public APIResponse bulkService(BulkBooksRequestDTO bulkBooksRequestDTO) {
        List<Book> booksEntity = new ArrayList<>();
//        bulkBooksRequestDTO.getBooks().forEach(each -> {
//            Book book = new Book();
//
//            book.setName(each.getName());
//            book.setDesc(each.getDesc());
//            book.setBookType(each.getBookType());
//            book.setYearOfPublication(each.getYearOfPublication());
//
//            booksEntity.add(book);
//
//        });

        for (int i = 0; i < 100000; i++) {
            Book book = new Book();

            book.setName("book-" + i);
            book.setDesc("book-" + i);
            book.setBookType("book-" + i);
            book.setYearOfPublication(2022);

            booksEntity.add(book);
        }

        bookRepository.saveAll(booksEntity);

        return new APIResponse();
    }
}
