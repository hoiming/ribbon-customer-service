package com.haiming.ribboncustomerservice.controller;


import com.haiming.ribboncustomerservice.integration.CoffeeService;
import com.haiming.ribboncustomerservice.model.Coffee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @GetMapping("/menu")
    @HystrixCommand(fallbackMethod = "fallbackReadMenu")
    public List<Coffee> readMenu(){
        List<Coffee> list = coffeeService.getAll();
        return list;
    }


    public List<Coffee> fallbackReadMenu(){
        return Collections.EMPTY_LIST;
    }
}
