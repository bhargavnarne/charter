package com.charter.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charter.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	Optional<Customer> findById(String id);
}