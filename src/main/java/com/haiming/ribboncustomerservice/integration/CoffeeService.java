package com.haiming.ribboncustomerservice.integration;

import com.haiming.ribboncustomerservice.model.Coffee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "waiter-service", contextId = "coffee", path="/coffee")
public interface CoffeeService {

    @GetMapping(path="/")
    List<Coffee> getAll();

    @GetMapping("/{id}")
    Coffee getById(@PathVariable Long id);
}
