package com.github.daylanbueno.happycustomer.resource;


import com.github.daylanbueno.happycustomer.domain.Filters.TransactionFilter;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionResources {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionDto registerTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.registerTransaction(transactionDto);
    }

    @GetMapping("/filter")
    public List<TransactionDto> findTransactionByFilter(TransactionFilter transactionFilter) {
        return transactionService.findTransactionByFilter(transactionFilter);
    }
}
