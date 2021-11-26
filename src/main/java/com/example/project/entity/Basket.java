package com.example.project.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.project.dto.ProductCount;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = "productCounts")
@Table(name = "basket")
public class Basket {

    @Id
    //    @GeneratedValue(generator = "UUID")
    private UUID id;

    // @Column
    // private List<UUID> products;

    @OneToMany(mappedBy = "basket")
    private Set<ProductCount> productCounts;
}
