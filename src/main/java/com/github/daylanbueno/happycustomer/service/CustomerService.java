package com.github.daylanbueno.happycustomer.service;

import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;

public interface CustomerService {
    public CustomerDto save(CustomerDto customerDto);
    public CustomerDto findById(Long id);
}
