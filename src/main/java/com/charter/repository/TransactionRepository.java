package com.charter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charter.domain.Customer;
import com.charter.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
	List<Transaction> findByCustomerAndCreatedDateBetweenAndTransactionAmountGreaterThan(Customer customer,
			Date startDateTime, Date endDateTime, Integer amount);
}