package com.example.project.service.impl;

import com.example.project.service.HttpService;
import com.example.project.controller.SimpleController;
import com.example.project.dto.AddBasketResourceRequest;
import com.example.project.dto.BasketDto;
import com.example.project.dto.CreateResourceRequest;
import com.example.project.dto.ProductCount;
import com.example.project.dto.ProductCountDto;
import com.example.project.dto.ProductCountKey;
import com.example.project.dto.RemoveBasketResourceRequest;
import com.example.project.dto.ResourceDto;
import com.example.project.dto.UpdateResourceRequest;
import com.example.project.entity.Basket;
import com.example.project.entity.Resource;
import com.example.project.repository.BasketRepository;
import com.example.project.repository.ProductCountRepository;
import com.example.project.repository.ResourceRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService {

    private final ResourceRepository resourceRepository;
    private final BasketRepository basketRepository;
    private final ProductCountRepository productCountRepository;

    @Override
    public List<ResourceDto> getResources() {
        log.info("Get all products");
        return StreamSupport.stream(resourceRepository.findAll().spliterator(),
                                    false)
            .map(resource -> ResourceDto.builder()
                 .name(resource.getName())
                 .id(resource.getId())
                 .build()
                 .add(WebMvcLinkBuilder.linkTo(SimpleController.class).slash("products").slash(resource.getId()).withSelfRel()))
            .collect(Collectors.toList());
    }

    @Override
    public ResourceDto getResource(UUID id) {
        log.info("Get product by id {}", id);

        final var resource = resourceRepository.findById(id);

        if (resource.isPresent()) {
            Link link = WebMvcLinkBuilder.linkTo(SimpleController.class).slash("products").slash(resource.get().getId()).withSelfRel();
            return ResourceDto.builder()
                .name(resource.get().getName())
                .id(resource.get().getId())
                .build()
                .add(link);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "Resource not found");
        }
    }

    @Override
    public void createResource(CreateResourceRequest request) {
        log.info("Create resource: {}", request);

        final var resource = Resource.builder()
            .name(request.getName())
            .id(UUID.randomUUID())
            .build();
        resourceRepository.save(resource);
    }

    @Override
    public void updateResource(UpdateResourceRequest resource) {
        log.info("Update resource: {}", resource);
        resourceRepository.save(Resource.builder()
                                .id(resource.getId())
                                .name(resource.getName())
                .build());
    }

    @Override
    public void deleteResource(UUID id) {
        log.info("Delete resource: {}", id);
        resourceRepository.deleteById(id);
    }

    @Override
    public List<BasketDto> getBaskets() {
        log.info("Get all baskets");
        return StreamSupport.stream(basketRepository.findAll().spliterator(),
                                    false)
            .map(basket -> BasketDto.builder()
                 .id(basket.getId())
                 .productCounts(ProductCountDto.getProductCountDtos(basket.getProductCounts())) 
                 .build()
                 .add(WebMvcLinkBuilder.linkTo(SimpleController.class).slash("baskets").slash(basket.getId()).withSelfRel()))
            .collect(Collectors.toList());
    }

    @Override
    public BasketDto createBasket() {
        final Basket basket = Basket.builder()
            .id(UUID.randomUUID())
            .build();
        log.info("Create basket: {}", basket.getId());
        basketRepository.save(basket);
        return BasketDto.builder()
            .id(basket.getId())
            .productCounts(new HashSet<ProductCountDto>())
            .build()
            .add(WebMvcLinkBuilder.linkTo(SimpleController.class).slash("baskets").slash(basket.getId()).withSelfRel());
    }

    @Override
    public BasketDto getBasket(UUID id) {
        log.info("Get basket: {}", id);
        final var basket = basketRepository.findById(id);
        if (basket.isPresent()) {
            Set<ProductCountDto> p;
            return BasketDto.builder()
                .id(basket.get().getId())
                .productCounts(ProductCountDto.getProductCountDtos(basket.get().getProductCounts())) 
                .build()
                .add(WebMvcLinkBuilder.linkTo(SimpleController.class).slash("baskets").slash(basket.get().getId()).withSelfRel());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "Basket not found");
        }
    }

    @Override
    public void deleteBasket(UUID id) {
        log.info("Delete basket: {}", id);
        basketRepository.deleteById(id);
    }

    @Override
    public void addBasketResource(UUID id,
                                  AddBasketResourceRequest resource) {
        log.info("Add resource to basket: basket {}, resource {}",
                 id, resource);
        final var basket = basketRepository.findById(id);
        if (basket.isPresent()) {
            if (resource.getCount() < 0) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                                                  "'count' must be non-negative integer");
            } else if (resource.getCount() == 0) {
                ProductCountKey pk = ProductCountKey.builder()
                    .productId(resource.getId())
                    .basketId(id)
                    .build();
                productCountRepository.deleteById(pk);
            } else {
                ProductCount pc = ProductCount.builder()
                    .id(ProductCountKey.builder()
                        .productId(resource.getId())
                        .basketId(id)
                        .build())
                    .product(resourceRepository
                             .findById(resource.getId()).get())
                    .basket(basket.get())
                    .productsCount(resource.getCount())
                    .build();
            
                productCountRepository.save(pc);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "Basket not found");
        }
    }

    @Override
    public void removeBasketResource(UUID id,
                                     RemoveBasketResourceRequest resource) {
        log.info("Remove resource from basket: basket {}, resource {}",
                 id, resource);
        final var basket = basketRepository.findById(id);
        if (basket.isPresent()) {
            productCountRepository.deleteById(new ProductCountKey(resource.getId(),
                                                                  basket.get().getId()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                              "Basket not found");
        }
    }
}
