package com.github.daylanbueno.happycustomer.service;

import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionGroupDto;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionDto registerTransaction(TransactionDto transactionDto);
    
    List<TransactionDto> findTranscationsByFilterDate(LocalDate startDate, LocalDate endDate);

    List<TransactionGroupDto> findTransactionGroupTheLastThreeMonth();

    List<TransactionDto> findTranscationsByFilterDateAndCustomer(LocalDate startDate, LocalDate endDate, Long id);

    List<TransactionDto> findAll();
}
