package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.project.entity.Basket;
import com.example.project.entity.Resource;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_count")
@Entity
public class ProductCount implements Serializable{
    @EmbeddedId
    private ProductCountKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    // @Column(name = "product_id")
    @JoinColumn(name = "product_id")
    Resource product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("basketId")
    // @Column(name = "basket_id")
    @JoinColumn(name = "basket_id")
    Basket basket;

    @Column(name = "products_count")
    int productsCount;
}
