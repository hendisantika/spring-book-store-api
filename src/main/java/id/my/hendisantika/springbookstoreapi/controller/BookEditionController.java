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
import id.my.hendisantika.springbookstoreapi.entity.BookEdition;
import id.my.hendisantika.springbookstoreapi.service.BookEditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

/**
 * REST Controller for BookEdition operations
 * Provides CRUD operations for managing book editions
 */
@RestController
@RequestMapping("/book-editions")
@RequiredArgsConstructor
public class BookEditionController {

    private final BookEditionService bookEditionService;

    /**
     * Get all book editions with optional pagination
     *
     * @param pageNumber Page number (optional)
     * @param pageSize   Page size (optional)
     * @param orderBy    Sort field (default: id)
     * @param sortBy     Sort direction (asc/desc, default: asc)
     * @return List of book editions
     */
    @GetMapping
    public ResponseEntity<APIResponse> getAllBookEditions(
            @RequestParam(value = "page-number", required = false) Integer pageNumber,
            @RequestParam(value = "page-size", required = false) Integer pageSize,
            @RequestParam(value = "order-by", defaultValue = "id") String orderBy,
            @RequestParam(value = "sort-by", defaultValue = "asc") String sortBy) {

        APIResponse response = new APIResponse();

        if (pageNumber != null && pageSize != null) {
            Sort.Direction direction = sortBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, orderBy));
            Page<BookEdition> editionsPage = bookEditionService.getAllBookEditions(pageable);
            response.setData(editionsPage);
        } else {
            List<BookEdition> editions = bookEditionService.getAllBookEditions();
            response.setData(editions);
        }

        response.setStatus(200);
        return ResponseEntity.ok(response);
    }

    /**
     * Get book edition by ID
     *
     * @param id Book edition ID
     * @return Book edition details
     */
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getBookEditionById(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        BookEdition edition = bookEditionService.getBookEditionById(id);

        if (edition != null) {
            response.setData(edition);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("Book edition not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Get book editions by book ID
     *
     * @param bookId Book ID
     * @return List of book editions for the specified book
     */
    @GetMapping("/book/{bookId}")
    public ResponseEntity<APIResponse> getBookEditionsByBookId(@PathVariable Long bookId) {
        APIResponse response = new APIResponse();
        List<BookEdition> editions = bookEditionService.getBookEditionsByBookId(bookId);
        response.setData(editions);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Create a new book edition
     *
     * @param bookEdition Book edition data
     * @return Created book edition
     */
    @PostMapping
    public ResponseEntity<APIResponse> createBookEdition(@RequestBody BookEdition bookEdition) {
        APIResponse response = new APIResponse();
        BookEdition createdEdition = bookEditionService.createBookEdition(bookEdition);
        response.setData(createdEdition);
        response.setStatus(201);

        return ResponseEntity.status(201).body(response);
    }

    /**
     * Update an existing book edition
     *
     * @param id          Book edition ID to update
     * @param bookEdition Updated book edition data
     * @return Updated book edition
     */
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateBookEdition(@PathVariable Long id, @RequestBody BookEdition bookEdition) {
        APIResponse response = new APIResponse();
        BookEdition updatedEdition = bookEditionService.updateBookEdition(id, bookEdition);

        if (updatedEdition != null) {
            response.setData(updatedEdition);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("Book edition not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Delete a book edition by ID
     *
     * @param id Book edition ID to delete
     * @return Deletion confirmation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteBookEdition(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        String result = bookEditionService.deleteBookEdition(id);
        response.setData(result);

        if (result.contains("successfully")) {
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }
}