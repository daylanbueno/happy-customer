package com.github.daylanbueno.happycustomer.repository;

import com.github.daylanbueno.happycustomer.domain.entity.Customer;
import com.github.daylanbueno.happycustomer.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    public List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
    public List<Transaction> findByDateBetweenAndCustomer(LocalDate startDate, LocalDate endDate, Customer customer);
}
