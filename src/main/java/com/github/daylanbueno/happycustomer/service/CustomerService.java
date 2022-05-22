package com.github.daylanbueno.happycustomer.service;

import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;

import java.util.Collection;
import java.util.List;

public interface CustomerService {
    public CustomerDto save(CustomerDto customerDto);
    public CustomerDto findById(Long id);
    public List<CustomerDto> findByIds(Collection<Long> ids);
}
