package com.example.project.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.project.controller.SimpleController;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCountDto extends RepresentationModel<ProductCountDto> {

    private UUID productId;

    private int count;

    public ProductCountDto(ProductCount pc) {
        this.productId = pc.getProduct().getId();
        this.count = pc.getProductsCount();
        add(WebMvcLinkBuilder.linkTo(SimpleController.class).slash("products").slash(productId).withSelfRel());
    }

    public static Set<ProductCountDto> getProductCountDtos(Set<ProductCount> s) {
        HashSet<ProductCountDto> dto = new HashSet<ProductCountDto>();
        for (var p : s) {
            dto.add(new ProductCountDto(p));
        }
        return dto;
    }
}
