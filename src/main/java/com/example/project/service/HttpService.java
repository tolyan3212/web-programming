package com.example.project.service;

import com.example.project.dto.AddBasketResourceRequest;
import com.example.project.dto.BasketDto;
import com.example.project.dto.CreateResourceRequest;
import com.example.project.dto.RemoveBasketResourceRequest;
import com.example.project.dto.ResourceDto;
import com.example.project.dto.UpdateResourceRequest;

import java.util.List;
import java.util.UUID;

public interface HttpService {

    public List<ResourceDto> getResources();

    public ResourceDto getResource(UUID id);

    public void createResource(CreateResourceRequest request);

    public void updateResource(UpdateResourceRequest request);

    public void deleteResource(UUID id);

    public List<BasketDto> getBaskets();

    public BasketDto createBasket();

    public BasketDto getBasket(UUID id);

    public void deleteBasket(UUID id);

    public void addBasketResource(UUID basket, AddBasketResourceRequest resource);

    public void removeBasketResource(UUID basket, RemoveBasketResourceRequest resource);
}
