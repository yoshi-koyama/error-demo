package com.example.errordemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> hello() {
        try {
            service.hello();
        } catch (Exception e) {
            throw new RuntimeException("something bad happened in hello() of HelloController", e);
        }
        return ResponseEntity.ok(Map.of("message", "hello world"));
    }

    @GetMapping("/hello2")
    public ResponseEntity<Map<String, String>> hello2() {
        try {
            service.hello2();
        } catch (Exception e) {
            throw new RuntimeException("something bad happened in hello2() of HelloController");
        }
        return ResponseEntity.ok(Map.of("message", "hello world"));
    }
}
