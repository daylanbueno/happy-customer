package com.github.daylanbueno.happycustomer.repository;

import com.github.daylanbueno.happycustomer.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Query(value = "SELECT t FROM Transaction t WHERE t.customer.id = :idCustomer")
    public List<Transaction> findTransactionByCustomer(@Param("idCustomer") Long idCustomer);
}
