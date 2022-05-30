package com.github.daylanbueno.happycustomer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private Long idCustomer;
    private String customerName;
    private LocalDate dateTransaction;
    private List<ItemDto> items;
    private BigDecimal total;
    private Integer totalPoint;
}
