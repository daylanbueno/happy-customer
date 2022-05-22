package com.github.daylanbueno.happycustomer.service.impl;

import com.github.daylanbueno.happycustomer.converters.CustomerConverter;
import com.github.daylanbueno.happycustomer.converters.ItemConverter;
import com.github.daylanbueno.happycustomer.converters.TransactionConverter;
import com.github.daylanbueno.happycustomer.domain.Filters.TransactionFilter;
import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.domain.dto.GroupDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionGroupDto;
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
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final ItemConverter itemConverter;
    private final CustomerConverter customerConverter;
    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final TransactionConverter transactionConverter;

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

        calculatePointsTransaction(transaction);

        Transaction newTransaction = transactionRepository.save(transaction);

        return transactionConverter.conveterToDTo(newTransaction);
    }


    @Override
    public List<TransactionDto> findTransactionByFilter(TransactionFilter transactionFilter) {
        List<Transaction> transactions = transactionRepository.findAll(transactionFilter.toSpecification());

        List<TransactionDto> transactionDtos = transactions
                .stream()
                .map(entity -> transactionConverter.conveterToDTo(entity))
                .collect(Collectors.toList());

        return transactionDtos;
    }

    @Override
    public List<TransactionGroupDto> findTransactionGroup(TransactionFilter transactionFilter) {
       List<TransactionGroupDto> transactionGroupDtos = new ArrayList<>();
        List<TransactionDto> transactionByFilter = findTransactionByFilter(transactionFilter);

        Collection<Long> idsCustomers = new HashSet<>();

        includIdCustomers(transactionByFilter, idsCustomers);

        List<CustomerDto> customers = customerService.findByIds(idsCustomers);

        List<LocalDate> dates = LocalDate.of(2022, LocalDate.now().getMonth().getValue() - 2, 01).datesUntil(LocalDate.now())
                .filter(date -> date.getDayOfMonth() == 1)
                .collect(Collectors.toList());


        for (CustomerDto customerDto: customers) {
            for (LocalDate currentDate: dates) {
                filterTransactionByCustomerAndDateMonth(customerDto,currentDate,transactionByFilter, transactionGroupDtos);
            }
        }

        customers.stream()
                .forEach(customer -> transactionGroupDtos.add(
                        TransactionGroupDto.builder()
                                .nameCustomer(customer.getName())
                                .build()));

        return transactionGroupDtos;
    }

    private void filterTransactionByCustomerAndDateMonth(CustomerDto customerDto, LocalDate currentDate, List<TransactionDto> transactionByFilter, List<TransactionGroupDto> transactionGroupDtos) {
        LocalDate start = currentDate.of(currentDate.getYear(), currentDate.getMonth().getValue(), 01);
        LocalDate end = currentDate.with(TemporalAdjusters.lastDayOfMonth());
        List<TransactionDto> result = transactionByFilter.stream().filter(transaction -> {
            return transaction.getIdCustomer() == customerDto.getId()
                    && currentDate.isAfter(start) && currentDate.isBefore(end);
        }).collect(Collectors.toList());


        TransactionGroupDto group = TransactionGroupDto.builder().nameCustomer(customerDto.getName()).build();

        result.stream().forEach(transact -> {
            group.getDetails().add(GroupDto.builder()
                    .moth(transact.getDateTransaction().getMonth().name())
                    .totalPoint(transact.getTotalPoint())
                    .build());
        });

    }

    private void includIdCustomers(List<TransactionDto> transactionByFilter, Collection<Long> idsCustomers) {
        List<Long> idCustomerRepeted = transactionByFilter.stream()
                .map(transaction -> transaction.getIdCustomer())
                .collect(Collectors.toList());
        idsCustomers.addAll(idCustomerRepeted);
    }

    private void calculatePointsTransaction(Transaction transaction) {
        int totalTransaction = transaction.getTotal().intValue();
        Integer totalPoint;

        if (totalTransaction <= 50) return;

        if(totalTransaction > 50 && totalTransaction < 100) {
            totalPoint = (totalTransaction - 50);
            transaction.setTotalPoint(totalPoint);
            return;
        }

        totalPoint = (totalTransaction - 50) * 1 + (totalTransaction - 100) * 1;
        transaction.setTotalPoint(totalPoint);
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
