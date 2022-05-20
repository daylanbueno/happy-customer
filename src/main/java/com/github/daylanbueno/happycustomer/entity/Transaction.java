package com.github.daylanbueno.happycustomer.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @OneToMany(mappedBy = "trasaction")
    private List<Item> items;
}
