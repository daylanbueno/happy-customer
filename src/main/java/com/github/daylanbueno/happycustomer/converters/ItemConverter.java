package com.github.daylanbueno.happycustomer.converters;

import com.github.daylanbueno.happycustomer.domain.dto.ItemDto;
import com.github.daylanbueno.happycustomer.domain.dto.ProductDto;
import com.github.daylanbueno.happycustomer.domain.entity.Item;
import com.github.daylanbueno.happycustomer.domain.entity.Product;
import com.github.daylanbueno.happycustomer.repository.ProductRepository;
import com.github.daylanbueno.happycustomer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItemConverter {

    private final ProductService productService;
    private final ProductConverter productConverter;

    public List<Item> converterDtoItensToEntity(List<ItemDto> itemsDto) {
       return itemsDto.stream()
                .map(itemDto ->  {
                    Product product  = findProductById(itemDto.getIdProduct());
                    return Item.builder().product(product).quantity(itemDto.getQuantity()).build();
                }).collect(Collectors.toList());
    }

    public List<ItemDto> converterDtoItensToDto(List<Item> items) {
        return items.stream()
                .map(entity ->  {
                    ProductDto productDto  = productService.findProductById(entity.getProduct().getId());
                    return ItemDto.builder().idProduct(productDto.getId()).quantity(entity.getQuantity()).build();
                }).collect(Collectors.toList());
    }

    private Product findProductById(Long id) {
        ProductDto productDto = productService.findProductById(id);
        return productConverter.converterToEntity(productDto);
    }
}
