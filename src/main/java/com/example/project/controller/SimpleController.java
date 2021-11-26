package com.example.project.controller;

import com.example.project.service.*;

import java.util.List;
import java.util.UUID;

import com.example.project.dto.AddBasketResourceRequest;
import com.example.project.dto.BasketDto;
import com.example.project.dto.CreateResourceRequest;
import com.example.project.dto.RemoveBasketResourceRequest;
import com.example.project.dto.UpdateResourceRequest;
import com.example.project.dto.ResourceDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleController {

    @Autowired
    private HttpService service;

    @GetMapping("/products")
    public List<ResourceDto> getResources() {
        return service.getResources();
    }

    @GetMapping("/products/{id}")
    public ResourceDto getResource(@PathVariable("id") UUID id) {
        return service.getResource(id);
    }

    @PostMapping("/products")
    public void createResource(@RequestBody CreateResourceRequest resource) {
        service.createResource(resource);
    }

    @PutMapping("/products/{id}")
    public void updateResource(@RequestBody UpdateResourceRequest resource) {
        service.updateResource(resource);
    }

    @DeleteMapping("/products/{id}")
    public void deleteResource(@PathVariable("id") UUID id) {
        service.deleteResource(id);
    }

    @GetMapping("/baskets")
    public List<BasketDto> getBaskets() {
        return service.getBaskets();
    }

    @PostMapping("/baskets")
    public BasketDto createBasket() {
        return service.createBasket();
    }

    @GetMapping("/baskets/{id}")
    public BasketDto getBasket(@PathVariable("id") UUID id) {
        return service.getBasket(id);
    }

    @DeleteMapping("/baskets/{id}")
    public void deleteBasket(@PathVariable("id") UUID id) {
        service.deleteBasket(id);
    }

    @PutMapping("/baskets/{id}/add_product")
    public void addBasketResource(@PathVariable("id") UUID basket,
                                  @RequestBody AddBasketResourceRequest request) {
        service.addBasketResource(basket, request);
    }

    @PutMapping("/baskets/{id}/remove_product")
    public void removeBasketResource(@PathVariable("id") UUID basket,
                                     @RequestBody RemoveBasketResourceRequest request) {
        service.removeBasketResource(basket, request);
    }

}
