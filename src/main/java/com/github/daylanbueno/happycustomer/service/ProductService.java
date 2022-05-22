package com.github.daylanbueno.happycustomer.service;

import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.domain.dto.ProductDto;
import com.github.daylanbueno.happycustomer.domain.entity.Product;

public interface ProductService {
    public ProductDto save(ProductDto productDto);
    public ProductDto findProductById(Long id);
}
