package com.github.daylanbueno.happycustomer.service.impl;

import com.github.daylanbueno.happycustomer.converters.CustomerConverter;
import com.github.daylanbueno.happycustomer.converters.ItemConverter;
import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.domain.entity.Item;
import com.github.daylanbueno.happycustomer.domain.entity.Transaction;
import com.github.daylanbueno.happycustomer.repository.TransactionRepository;
import com.github.daylanbueno.happycustomer.service.CustomerService;
import com.github.daylanbueno.happycustomer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final ItemConverter itemConverter;
    private final CustomerConverter customerConverter;
    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;

    private static BigDecimal calculateTotal(Item item) {
        return item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
    }

    @Override
    public TransactionDto registerTransaction(TransactionDto transactionDto) {

        CustomerDto customerDto = customerService.findById(transactionDto.getIdCustomer());

        Transaction transaction = Transaction.builder()
                .date(LocalDate.now())
                .customer(customerConverter.converterToEntity(customerDto))
                .items(itemConverter.converterDtoItensToEntity(transactionDto.getItems()))
                .build();

        calculateTotalTransaction(transaction);

        Transaction newTransaction = transactionRepository.save(transaction);

        return TransactionDto.builder()
                .idCustomer(newTransaction.getCustomer().getId())
                .dateTransaction(newTransaction.getDate())
                .total(newTransaction.getTotal())
                .items(itemConverter.converterDtoItensToDto(newTransaction.getItems()))
                .build();
    }

    private void calculateTotalTransaction(Transaction transaction) {
        BigDecimal total = transaction
                .getItems()
                .stream()
                .map(TransactionServiceImpl::calculateTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        transaction.setTotal(total);
    }

}
