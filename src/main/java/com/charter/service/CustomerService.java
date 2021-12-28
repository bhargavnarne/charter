package com.charter.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.charter.domain.Customer;
import com.charter.domain.Transaction;
import com.charter.dto.ResponseDTO;
import com.charter.dto.RewardRequestDTO;
import com.charter.dto.RewardResponseDTO;
import com.charter.repository.CustomerRepository;
import com.charter.repository.TransactionRepository;

public class CustomerService {
	private static final String DATE_TIME_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ssZ";

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Value("${min.dollar.eligible.min.rewardpoints}")
	private int amtEligibleForMinRewardPoints;

	@Value("${min.dollar.eligible.max.rewardpoints}")
	private int amtEligibleForMaxRewardPoints;

	@Value("${max.rewardpoints}")
	private int maxRewardPoints;

	@Value("${min.rewardpoints}")
	private int minRewardPoints;

	public ResponseDTO getRewardPoints(String customerId, RewardRequestDTO rewardRequestDTO) {
		ResponseDTO responseDTO;
		Date fromDate;
		Date toDate;
		try {
			fromDate = convertStringToDate(rewardRequestDTO.getFromDate());
			toDate = convertStringToDate(rewardRequestDTO.getToDate());
		} catch (ParseException parseEx) {
			// record the exception
			return new ResponseDTO("Invalid date time format.");
		}

		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (customerOptional.isPresent()) {
			Customer customer = customerOptional.get();
			List<Transaction> transactionsList = transactionRepository
					.findByCustomerAndCreatedDateBetweenAndTransactionAmountGreaterThan(customer, fromDate, toDate,
							amtEligibleForMinRewardPoints);
			int rewardPoints = processRewards(transactionsList);
			responseDTO = new RewardResponseDTO("SUCCESSFUL", customer.getId(), customer.getCustomerName(),
					Integer.toString(rewardPoints));
		} else {
			responseDTO = new ResponseDTO("Customer couldn't be found");
		}

		return responseDTO;
	}

	private int processRewards(List<Transaction> transactionList) {
		int totalRewardPoints = 0;

		for (Transaction transaction : transactionList) {
			int rewardPoints = calculatePoints(transaction);
			totalRewardPoints += rewardPoints;
		}

		return totalRewardPoints;
	}

	private int calculatePoints(Transaction transaction) {
		int eligibleAmountForMaxPoints = 0;
		int transactionAmount = Math.round(transaction.getTransactionAmount());

		if (transactionAmount > amtEligibleForMaxRewardPoints) {

			eligibleAmountForMaxPoints = transactionAmount - (amtEligibleForMaxRewardPoints);
		}

		int eligibleAmountForMinPoints = transactionAmount - eligibleAmountForMaxPoints
				- (amtEligibleForMinRewardPoints);

		return (eligibleAmountForMaxPoints * maxRewardPoints) + (eligibleAmountForMinPoints * minRewardPoints);
	}

	public static Date convertStringToDate(String dateString) throws ParseException {
		SimpleDateFormat dateTimeWithTimeZoneFormat = new SimpleDateFormat(DATE_TIME_WITH_TIMEZONE);
		return dateTimeWithTimeZoneFormat.parse(dateString);
	}
}
