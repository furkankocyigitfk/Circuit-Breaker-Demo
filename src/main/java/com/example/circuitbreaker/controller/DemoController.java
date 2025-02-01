package com.example.circuitbreaker.controller;

import com.example.circuitbreaker.service.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@RestController
@AllArgsConstructor
public class DemoController {
    private DemoService demoService;

    @GetMapping("/circuit-breaker")
    ResponseEntity<String> demo(@RequestParam("isBusinessException") Boolean isBusinessException, @RequestParam("waitTime") Long waitTime) throws InterruptedException, TimeoutException {
        return ResponseEntity.ok(demoService.call(isBusinessException, waitTime));
    }
}
