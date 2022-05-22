package com.github.daylanbueno.happycustomer.converters;

import com.github.daylanbueno.happycustomer.domain.dto.ProductDto;
import com.github.daylanbueno.happycustomer.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    public Product converterToEntity(ProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice()).build();
    }

    public ProductDto converterToDto(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice()).build();
    }
}
