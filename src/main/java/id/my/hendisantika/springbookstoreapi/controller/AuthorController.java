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
import id.my.hendisantika.springbookstoreapi.entity.Author;
import id.my.hendisantika.springbookstoreapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
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

/**
 * REST Controller for Author operations
 * Provides CRUD operations for managing authors
 */
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    /**
     * Get all authors with pagination
     *
     * @param pageNumber Page number (default: 0)
     * @param pageSize   Page size (default: 10)
     * @param orderBy    Sort field (default: id)
     * @param sortBy     Sort direction (asc/desc, default: asc)
     * @return Paginated list of authors
     */
    @GetMapping
    public ResponseEntity<APIResponse> getAllAuthors(
            @RequestParam(value = "page-number", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order-by", defaultValue = "id") String orderBy,
            @RequestParam(value = "sort-by", defaultValue = "asc") String sortBy) {

        Sort.Direction direction = sortBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, orderBy));

        APIResponse response = authorService.getAuthors(pageable);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Get authors with named query (ordered by ID desc)
     *
     * @param pageNumber Page number (default: 0)
     * @param pageSize   Page size (default: 10)
     * @return Paginated list of authors
     */
    @GetMapping("/ordered")
    public ResponseEntity<APIResponse> getAuthorsOrdered(
            @RequestParam(value = "page-number", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        APIResponse response = authorService.getAuthorsWithNamedQuery(pageable);
        response.setStatus(200);

        return ResponseEntity.ok(response);
    }

    /**
     * Get author by ID
     *
     * @param id Author ID
     * @return Author details
     */
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getAuthorById(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        Author author = authorService.getAuthorById(id);

        if (author != null) {
            response.setData(author);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("Author not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Create a new author
     *
     * @param author Author data
     * @return Created author
     */
    @PostMapping
    public ResponseEntity<APIResponse> createAuthor(@RequestBody Author author) {
        APIResponse response = new APIResponse();
        Author createdAuthor = authorService.createAuthor(author);
        response.setData(createdAuthor);
        response.setStatus(201);

        return ResponseEntity.status(201).body(response);
    }

    /**
     * Update an existing author
     *
     * @param id     Author ID to update
     * @param author Updated author data
     * @return Updated author
     */
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        APIResponse response = new APIResponse();
        Author updatedAuthor = authorService.updateAuthor(id, author);

        if (updatedAuthor != null) {
            response.setData(updatedAuthor);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("Author not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Delete an author by ID
     *
     * @param id Author ID to delete
     * @return Deletion confirmation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteAuthor(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        String result = authorService.deleteAuthor(id);
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