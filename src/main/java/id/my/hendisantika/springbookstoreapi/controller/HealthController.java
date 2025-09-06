package id.my.hendisantika.springbookstoreapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple health check controller
 */
@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Spring Book Store API is running!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}