package com.github.daylanbueno.happycustomer.service.impl;

import com.github.daylanbueno.happycustomer.converters.ProductConverter;
import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.domain.dto.ProductDto;
import com.github.daylanbueno.happycustomer.domain.entity.Customer;
import com.github.daylanbueno.happycustomer.domain.entity.Product;
import com.github.daylanbueno.happycustomer.exception.BusinessException;
import com.github.daylanbueno.happycustomer.repository.CustomerRepository;
import com.github.daylanbueno.happycustomer.repository.ProductRepository;
import com.github.daylanbueno.happycustomer.service.CustomerService;
import com.github.daylanbueno.happycustomer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public ProductDto save(ProductDto productDto) {
        Product entity = productConverter.converterToEntity(productDto);
        Product newProduct = productRepository.save(entity);
        return productConverter.converterToDto(newProduct);
    }

    @Override
    public ProductDto findProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> productConverter.converterToDto(product))
                .orElseThrow(() -> new BusinessException("Product not found"));
    }
}
