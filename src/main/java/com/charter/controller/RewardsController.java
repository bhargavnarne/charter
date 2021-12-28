package com.charter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.dto.ResponseDTO;
import com.charter.dto.RewardRequestDTO;
import com.charter.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class RewardsController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/rewardpoints/{customerId}")
	public ResponseEntity<Object> addCustomer(@PathVariable("customerId") String customerId,
			@RequestBody RewardRequestDTO rewardRequestDTO) {
		ResponseDTO savedCustomer = customerService.getRewardPoints(customerId, rewardRequestDTO);
		return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
	}

}