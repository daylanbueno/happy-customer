package com.github.daylanbueno.happycustomer;

import com.github.daylanbueno.happycustomer.domain.dto.ItemDto;
import com.github.daylanbueno.happycustomer.domain.dto.TransactionDto;
import com.github.daylanbueno.happycustomer.domain.entity.Customer;
import com.github.daylanbueno.happycustomer.domain.entity.Product;
import com.github.daylanbueno.happycustomer.domain.entity.Transaction;
import com.github.daylanbueno.happycustomer.repository.CustomerRepository;
import com.github.daylanbueno.happycustomer.repository.ProductRepository;
import com.github.daylanbueno.happycustomer.repository.TransactionRepository;
import com.github.daylanbueno.happycustomer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class HappyCustomerApplication  implements CommandLineRunner {

	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final TransactionRepository transactionRepository;

	private final TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication.run(HappyCustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer custme1 = Customer.builder().name("Marcos").build();
		Customer newcustomer = customerRepository.save(custme1);

		Product product = Product.builder().name("Notebook").price(BigDecimal.valueOf(100)).build();
		Product newProduct = productRepository.save(product);

		ItemDto item1 = ItemDto.builder().idProduct(newProduct.getId()).quantity(2).build();
		ItemDto item2 = ItemDto.builder().idProduct(newProduct.getId()).quantity(3).build();
		ItemDto item3 = ItemDto.builder().idProduct(newProduct.getId()).quantity(5).build();


		TransactionDto transaction1 = TransactionDto.builder()
				.idCustomer(newcustomer.getId())
				.items(Arrays.asList(item1))
				.build();

		TransactionDto transaction2 = TransactionDto.builder()
				.idCustomer(newcustomer.getId())
				.items(Arrays.asList(item1, item2))
				.build();

		TransactionDto transaction3 = TransactionDto.builder()
				.idCustomer(newcustomer.getId())
				.items(Arrays.asList(item1, item3))
				.build();

		TransactionDto newtransactionDto1 = transactionService.registerTransaction(transaction1);
		TransactionDto newtransactionDto2 = transactionService.registerTransaction(transaction2);
		TransactionDto newtransactionDto3 = transactionService.registerTransaction(transaction3);

		Transaction transactionUpdate1 = transactionRepository.findById(newtransactionDto1.getId()).orElse(null);
		Transaction transactionUpdate2 = transactionRepository.findById(newtransactionDto2.getId()).orElse(null);
		Transaction transactionUpdate3 = transactionRepository.findById(newtransactionDto3.getId()).orElse(null);

		transactionUpdate1.setDate(LocalDate.now());
		transactionUpdate2.setDate(LocalDate.now().minusMonths(2));
		transactionUpdate3.setDate(LocalDate.now().minusMonths(3));

		transactionRepository.save(transactionUpdate1);
		transactionRepository.save(transactionUpdate2);
		transactionRepository.save(transactionUpdate3);

	}
}
