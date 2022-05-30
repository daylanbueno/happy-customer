package com.github.daylanbueno.happycustomer.resource;


import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionGroupDto;
import com.github.daylanbueno.happycustomer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/filter/customer/{id}")
    public List<TransactionDto> findTranscationsByFilterDateAndCustomer(@RequestParam("startDate")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                             @RequestParam("endDate")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                             @PathVariable("id") Long id) {
        return transactionService.findTranscationsByFilterDateAndCustomer(startDate, endDate, id);
    }

    @GetMapping
    public List<TransactionDto> findAll() {
        return transactionService.findAll();
    }


    @GetMapping("/filter/date")
    public List<TransactionDto> findTranscationsByFilterDate(@RequestParam("startDate")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                             @RequestParam("endDate")
                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return transactionService.findTranscationsByFilterDate(startDate, endDate);
    }

    @GetMapping("/group-last-three-month")
    public List<TransactionGroupDto> findTransactionGroupTheLastThreeMonth() {
        return transactionService.findTransactionGroupTheLastThreeMonth();
    }
}
