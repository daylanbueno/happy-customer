package com.github.daylanbueno.happycustomer.resource;


import com.github.daylanbueno.happycustomer.domain.dto.CustomerDto;
import com.github.daylanbueno.happycustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerResources {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @GetMapping("/{id}")
    public CustomerDto findById(@PathVariable Long id) {
        return customerService.findById(id);
    }
}
