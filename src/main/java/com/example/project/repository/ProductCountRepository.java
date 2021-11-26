package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.example.project.dto.ProductCount;
import com.example.project.dto.ProductCountKey;

@Repository
public interface ProductCountRepository extends CrudRepository<ProductCount, ProductCountKey> {
}
