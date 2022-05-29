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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer entity = Customer.builder().id(customerDto.getId()).name(customerDto.getName()).build();
        Customer newCustomer = customerRepository.save(entity);
        return CustomerDto.builder()
                .id(newCustomer.getId())
                .name(newCustomer.getName()).build();
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customers =  customerRepository.findAll();
        return customers.stream()
                .map(entity -> customerConverter.converterToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository
                .findById(id).orElseThrow(() -> new BusinessException("Customer not found!"));
        return  customerConverter.converterToDto(customer);
    }
}
