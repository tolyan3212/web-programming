package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.example.project.entity.Basket;

@Repository
public interface BasketRepository extends CrudRepository<Basket, UUID> {
}
