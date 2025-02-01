package com.example.circuitbreaker.service;

import com.example.circuitbreaker.exception.BusinessException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
public class DemoService {
    @CircuitBreaker(name = "demoService", fallbackMethod = "fallback")
    public String call(Boolean isBusinessException, Long waitTime) throws TimeoutException, InterruptedException {
        if (Boolean.TRUE.equals(isBusinessException)) {
            throw new BusinessException("Business Exception");
        }
        if (waitTime.compareTo(1000L) > 0) {
            throw new TimeoutException("Timeout exception");
        }
        Thread.sleep(waitTime);
        return "Service call is successful";
    }

    public String fallback(Exception e) {
        return "Fallback: " + e.getMessage();
    }
}
