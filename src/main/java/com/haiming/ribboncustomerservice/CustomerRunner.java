package com.haiming.ribboncustomerservice;

import com.haiming.ribboncustomerservice.integration.CoffeeService;
import com.haiming.ribboncustomerservice.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CustomerRunner implements ApplicationRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CoffeeService coffeeService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        showServiceInstance();
        readMenuUsingFeign();
        Coffee coffee = coffeeService.getById(1L);
        System.out.println("Coffee with id = 1: " + coffee);
    }

    private void readMenuUsingFeign() {
        List<Coffee> coffees = coffeeService.getAll();
        coffees.forEach(c -> System.out.println("Coffee: " + c));
    }

    private void readMenu() {
        ParameterizedTypeReference<List<Coffee>> ptr =
                new ParameterizedTypeReference<List<Coffee>>() {
                };
        ResponseEntity<List<Coffee>> list = restTemplate.exchange("http://waiter-service/coffee/", HttpMethod.GET, null, ptr);
        list.getBody().forEach(c -> System.out.println("Coffee: " + c));
    }

    private void showServiceInstance() {
        System.out.println("DiscoveryClient: " + discoveryClient.getClass().getName());
        discoveryClient.getInstances("waiter-service").forEach(s -> {
            System.out.println("Host: " + s.getHost() + ", " + s.getPort());
        });
    }

}
