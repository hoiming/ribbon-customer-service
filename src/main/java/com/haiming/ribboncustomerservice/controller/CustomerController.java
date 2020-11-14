package com.haiming.ribboncustomerservice.controller;


import com.haiming.ribboncustomerservice.integration.CoffeeService;
import com.haiming.ribboncustomerservice.model.Coffee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CoffeeService coffeeService;


    private CircuitBreaker circuitBreaker;
    private Bulkhead bulkhead;

    public CustomerController(CircuitBreakerRegistry circuitBreakerRegistry, BulkheadRegistry bulkheadRegistry){
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("menu");
        bulkhead = bulkheadRegistry.bulkhead("menu");

    }
    @GetMapping("/menu")
    public List<Coffee> readMenu(){
        return Try.ofSupplier(CircuitBreaker.decorateSupplier(circuitBreaker,
                () -> coffeeService.getAll()))
                .recover(CircuitBreakerOpenException.class, Collections.emptyList())
                .recover(BulkheadFullException.class, Collections.emptyList())
                .get();
    }


    public List<Coffee> fallbackReadMenu(){
        return Collections.EMPTY_LIST;
    }
}
