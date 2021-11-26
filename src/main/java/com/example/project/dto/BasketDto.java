package com.example.project.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @Entity
// @Table(name = "basket")
public class BasketDto extends RepresentationModel<BasketDto> {

    private UUID id;

    // @Column
    // private List<UUID> products;

    private Set<ProductCountDto> productCounts;
}
