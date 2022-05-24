package com.github.daylanbueno.happycustomer.service.impl;

import com.github.daylanbueno.happycustomer.converters.CustomerConverter;
import com.github.daylanbueno.happycustomer.converters.TransactionConverter;
import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.domain.entity.Customer;
import com.github.daylanbueno.happycustomer.domain.entity.Transaction;
import com.github.daylanbueno.happycustomer.exception.BusinessException;
import com.github.daylanbueno.happycustomer.repository.CustomerRepository;
import com.github.daylanbueno.happycustomer.repository.TransactionRepository;
import com.github.daylanbueno.happycustomer.service.CustomerService;
import com.github.daylanbueno.happycustomer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer entity = Customer.builder().id(customerDto.getId()).name(customerDto.getName()).build();
        Customer newCustomer = customerRepository.save(entity);
        return CustomerDto.builder()
                .id(newCustomer.getId())
                .name(newCustomer.getName()).build();
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository
                .findById(id).orElseThrow(() -> new BusinessException("Customer not found!"));

        List<Transaction> transactionsByCustomer = transactionRepository.findTransactionByCustomer(customer.getId());

        List<TransactionDto> transactionsDto = transactionsByCustomer.stream()
                .map(entity -> transactionConverter.conveterToDTo(entity))
                .collect(Collectors.toList());

        CustomerDto customerDto = customerConverter.converterToDto(customer);
        customerDto.setTransactionsDtos(transactionsDto);

        return customerDto;
    }

    @Override
    public List<CustomerDto> findByIds(Collection<Long> ids) {
        List<Customer> customers = customerRepository.findAllById(ids);
        return customers.stream()
                .map(entity -> customerConverter.converterToDto(entity))
                .collect(Collectors.toList());
    }
}
