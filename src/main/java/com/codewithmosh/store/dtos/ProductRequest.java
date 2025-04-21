package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
}
