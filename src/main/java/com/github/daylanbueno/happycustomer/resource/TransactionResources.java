package com.github.daylanbueno.happycustomer.resource;


import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.service.CustomerService;
import com.github.daylanbueno.happycustomer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionResources {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionDto registerTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.registerTransaction(transactionDto);
    }
}
