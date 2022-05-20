package com.github.daylanbueno.happycustomer.resource;


import com.github.daylanbueno.happycustomer.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.entity.Customer;
import com.github.daylanbueno.happycustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerResources {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }
}
