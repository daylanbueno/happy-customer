package com.github.daylanbueno.happycustomer.service.impl;

import com.github.daylanbueno.happycustomer.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.entity.Customer;
import com.github.daylanbueno.happycustomer.repository.CustomerRepository;
import com.github.daylanbueno.happycustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer entity = Customer.builder().id(customerDto.getId()).name(customerDto.getName()).build();
        Customer newCustomer = customerRepository.save(entity);
        return CustomerDto.builder()
                .id(newCustomer.getId()).name(newCustomer.getName()).build();
    }
}
