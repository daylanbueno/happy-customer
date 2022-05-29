package com.github.daylanbueno.happycustomer.service;

import com.github.daylanbueno.happycustomer.domain.Filters.TransactionFilter;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionGroupDto;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionDto registerTransaction(TransactionDto transactionDto);

    List<TransactionDto> findTransactionByFilter(TransactionFilter transactionFilter);

    List<TransactionDto> findTranscationsByIdCustomer(Long idCustomer);

    List<TransactionDto> findTranscationsByFilterDate(LocalDate startDate, LocalDate endDate);

    List<TransactionGroupDto> findTransactionGroup(TransactionFilter transactionFilter);

    List<TransactionGroupDto> findTransactionGroupTheLastThreeMonth();
}
