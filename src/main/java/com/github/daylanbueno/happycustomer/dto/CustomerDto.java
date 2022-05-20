package com.github.daylanbueno.happycustomer.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {
    private Long id;
    private String name;
}
