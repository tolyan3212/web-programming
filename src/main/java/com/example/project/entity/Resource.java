package com.example.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.project.dto.ProductCount;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "resource")
@Builder
@Data
@EqualsAndHashCode(exclude = "productCounts")
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    // @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "product")
    Set<ProductCount> productCounts;
}
