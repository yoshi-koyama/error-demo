package com.example.errordemo;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello() {
        if (true) {
            throw new RuntimeException("something bad happened in hello() of HelloService");
        }
        return "hello";
    }

    public String hello2() {
        if (true) {
            throw new RuntimeException("something bad happened in hello2() of HelloService");
        }
        return "hello2";
    }
}
