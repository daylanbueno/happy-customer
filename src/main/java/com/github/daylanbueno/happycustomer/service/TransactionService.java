package com.github.daylanbueno.happycustomer.service;

import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;

public interface TransactionService {
    TransactionDto registerTransaction(TransactionDto transactionDto);
}
