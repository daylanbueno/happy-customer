package com.github.daylanbueno.happycustomer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionGroupDto {
    private String nameCustomer;
    private String moth;
    private Integer totalPointMonth;
    List<GroupDto> details;
}
