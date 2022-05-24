package com.github.daylanbueno.happycustomer.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CustomerDto {
    private Long id;
    private String name;
    private List<TransactionDto> transactionsDtos;
}
