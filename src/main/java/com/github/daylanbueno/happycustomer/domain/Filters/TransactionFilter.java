package com.github.daylanbueno.happycustomer.domain.Filters;

import com.github.daylanbueno.happycustomer.domain.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFilter {
    private Long idCustomer;
    private LocalDate startDate;
    private LocalDate endDate;

    public Specification<Transaction> toSpecification() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (idCustomer != null) {
                Path<Long> filteCustomer = root.<Long>get("customer_id");
                Predicate predicateCustomer = builder.equal(filteCustomer, idCustomer);
                predicates.add(predicateCustomer);
            }
            if (startDate != null) {
                Path<LocalDate> fieldDate = root.<LocalDate>get("date");
                Predicate predicateDate = builder.between(fieldDate, startDate, endDate);
                predicates.add(predicateDate);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
