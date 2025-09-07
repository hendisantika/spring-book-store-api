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
import id.my.hendisantika.springbookstoreapi.dto.LoginRequestDTO;
import id.my.hendisantika.springbookstoreapi.dto.SignUpRequestDTO;
import id.my.hendisantika.springbookstoreapi.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Authentication operations
 * Handles user registration and login
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * User registration endpoint
     *
     * @param signUpRequest User registration data
     * @return Registration response with access token
     */
    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequest) {
        APIResponse response = loginService.signUp(signUpRequest);

        if (response.getData() != null) {
            response.setStatus(201);
            return ResponseEntity.status(201).body(response);
        } else {
            response.setStatus(400);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * User login endpoint
     *
     * @param loginRequest User login credentials
     * @return Login response with access token
     */
    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequest) {
        APIResponse response = loginService.login(loginRequest);

        if (response.getData() != null && !response.getData().toString().contains("failed")) {
            response.setStatus(200);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(401);
            response.setError("Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }

    /**
     * Health check for authentication service
     *
     * @return Service status
     */
    @GetMapping("/auth/health")
    public ResponseEntity<APIResponse> authHealth() {
        APIResponse response = new APIResponse();
        response.setData("Authentication service is running");
        response.setStatus(200);
        return ResponseEntity.ok(response);
    }
}