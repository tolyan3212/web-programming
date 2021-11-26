package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.example.project.entity.Resource;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, UUID> {
}
