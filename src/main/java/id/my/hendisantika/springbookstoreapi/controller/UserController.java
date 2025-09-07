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
import id.my.hendisantika.springbookstoreapi.entity.User;
import id.my.hendisantika.springbookstoreapi.service.UserService;
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
 * REST Controller for User operations
 * Provides CRUD operations for managing users
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Get all users with optional pagination
     *
     * @param pageNumber Page number (optional)
     * @param pageSize   Page size (optional)
     * @param orderBy    Sort field (default: id)
     * @param sortBy     Sort direction (asc/desc, default: asc)
     * @return List of users
     */
    @GetMapping
    public ResponseEntity<APIResponse> getAllUsers(
            @RequestParam(value = "page-number", required = false) Integer pageNumber,
            @RequestParam(value = "page-size", required = false) Integer pageSize,
            @RequestParam(value = "order-by", defaultValue = "id") String orderBy,
            @RequestParam(value = "sort-by", defaultValue = "asc") String sortBy) {

        APIResponse response = new APIResponse();

        if (pageNumber != null && pageSize != null) {
            Sort.Direction direction = sortBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, orderBy));
            Page<User> usersPage = userService.getAllUsers(pageable);
            response.setData(usersPage);
        } else {
            List<User> users = userService.getAllUsers();
            response.setData(users);
        }

        response.setStatus(200);
        return ResponseEntity.ok(response);
    }

    /**
     * Get user by ID
     *
     * @param id User ID
     * @return User details
     */
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserById(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        User user = userService.getUserById(id);

        if (user != null) {
            user.setPassword(null);
            response.setData(user);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("User not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Get user profile (requires authentication)
     *
     * @param email User email from JWT token
     * @return User profile
     */
    @GetMapping("/profile")
    public ResponseEntity<APIResponse> getUserProfile(@RequestParam("email") String email) {
        APIResponse response = new APIResponse();
        User user = userService.getUserByEmail(email);

        if (user != null) {
            user.setPassword(null);
            response.setData(user);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("User not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Create a new user
     *
     * @param user User data
     * @return Created user
     */
    @PostMapping
    public ResponseEntity<APIResponse> createUser(@RequestBody User user) {
        APIResponse response = new APIResponse();
        User createdUser = userService.createUser(user);
        createdUser.setPassword(null);
        response.setData(createdUser);
        response.setStatus(201);

        return ResponseEntity.status(201).body(response);
    }

    /**
     * Update user profile
     *
     * @param id   User ID to update
     * @param user Updated user data
     * @return Updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateUser(@PathVariable Long id, @RequestBody User user) {
        APIResponse response = new APIResponse();
        User updatedUser = userService.updateUser(id, user);

        if (updatedUser != null) {
            updatedUser.setPassword(null);
            response.setData(updatedUser);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("User not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Update user profile (authenticated endpoint)
     *
     * @param email User email from JWT token
     * @param user  Updated user data
     * @return Updated user profile
     */
    @PutMapping("/profile")
    public ResponseEntity<APIResponse> updateUserProfile(@RequestParam("email") String email, @RequestBody User user) {
        APIResponse response = new APIResponse();
        User existingUser = userService.getUserByEmail(email);

        if (existingUser != null) {
            User updatedUser = userService.updateUser(existingUser.getId(), user);
            updatedUser.setPassword(null);
            response.setData(updatedUser);
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setError("User not found");
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Delete a user by ID
     *
     * @param id User ID to delete
     * @return Deletion confirmation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        String result = userService.deleteUser(id);
        response.setData(result);

        if (result.contains("successfully")) {
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Deactivate a user account
     *
     * @param id User ID to deactivate
     * @return Deactivation confirmation
     */
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<APIResponse> deactivateUser(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        String result = userService.deactivateUser(id);
        response.setData(result);

        if (result.contains("successfully")) {
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(404);
            return ResponseEntity.status(404).body(response);
        }
    }

    /**
     * Activate a user account
     *
     * @param id User ID to activate
     * @return Activation confirmation
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<APIResponse> activateUser(@PathVariable Long id) {
        APIResponse response = new APIResponse();
        String result = userService.activateUser(id);
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