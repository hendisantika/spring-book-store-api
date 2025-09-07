package id.my.hendisantika.springbookstoreapi.controller;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:40
 * To change this template use File | Settings | File Templates.
 */

import id.my.hendisantika.springbookstoreapi.common.APIResponse;
import id.my.hendisantika.springbookstoreapi.dto.BookDTO;
import id.my.hendisantika.springbookstoreapi.dto.BookRequestDTO;
import id.my.hendisantika.springbookstoreapi.dto.BulkBooksRequestDTO;
import id.my.hendisantika.springbookstoreapi.entity.Book;
import id.my.hendisantika.springbookstoreapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * REST Controller for Book operations
 * Provides CRUD operations for managing books
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * Get all books with optional filters
     *
     * @param yop      Year of publication filter (comma-separated values)
     * @param bookType Book type filter
     * @return List of books
     */
    @GetMapping
    public ResponseEntity<APIResponse> getAllBooks(
            @RequestParam(value = "yop", required = false) Set<Integer> yop,
            @RequestParam(value = "bookType", required = false) String bookType) {

        APIResponse response = new APIResponse();
        List<Book> books = bookService.getBooks(yop, bookType);
        response.setData(books);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Get book by ID
     *
     * @param id         Book ID
     * @param authorData Include author data in response
     * @return Book details
     */
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getBookById(
            @PathVariable Long id,
            @RequestParam(value = "authorData", defaultValue = "false") boolean authorData) {

        APIResponse response = new APIResponse();
        BookDTO book = bookService.getBookById(id, authorData);
        response.setData(book);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Create a new book
     *
     * @param bookRequest Book creation request
     * @return Created book
     */
    @PostMapping
    public ResponseEntity<APIResponse> createBook(@RequestBody BookRequestDTO bookRequest) {
        APIResponse response = new APIResponse();
        Book createdBook = bookService.createBook(bookRequest);
        response.setData(createdBook);
        response.setStatus(201);

        return ResponseEntity.status(201).body(response);
    }

    /**
     * Update an existing book
     *
     * @param id   Book ID to update
     * @param book Updated book data
     * @return Updated book
     */
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateBook(@PathVariable Long id, @RequestBody Book book) {
        APIResponse response = new APIResponse();
        book.setId(id);
        Book updatedBook = bookService.updateBook(book);
        response.setData(updatedBook);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Delete a book by ID
     *
     * @param id Book ID to delete
     * @return Deletion confirmation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteBook(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        String result = bookService.deleteById(id);
        response.setData(result);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Get books by raw query
     *
     * @param yop Year of publication filter
     * @return Books matching criteria
     */
    @GetMapping("/raw")
    public ResponseEntity<APIResponse> getBooksByRawQuery(
            @RequestParam("yop") Set<Integer> yop) {

        APIResponse response = bookService.getBooksByRawQuery(yop);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Get books using QueryDSL
     *
     * @param year Year filter
     * @return Books matching criteria
     */
    @GetMapping("/querydsl")
    public ResponseEntity<APIResponse> getBooksByQueryDsl(
            @RequestParam("year") Integer year) {

        APIResponse response = bookService.getBooksByQueryDsl(year);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Bulk create books
     *
     * @param bulkBooksRequest Bulk book creation request
     * @return Bulk creation result
     */
    @PostMapping("/bulk")
    public ResponseEntity<APIResponse> bulkCreateBooks(@RequestBody BulkBooksRequestDTO bulkBooksRequest) {
        APIResponse response = bookService.bulkService(bulkBooksRequest);
        response.setStatus(201);

        return ResponseEntity.status(201).body(response);
    }

    /**
     * Test exception handling
     *
     * @param yop Year of publication for division test
     * @return Result or exception
     */
    @GetMapping("/test-exception")
    public ResponseEntity<APIResponse> testException(@RequestParam("yop") Integer yop) {
        APIResponse response = bookService.getCaughtException(yop);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }
}