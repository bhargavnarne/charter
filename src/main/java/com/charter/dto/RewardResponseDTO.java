package com.charter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RewardResponseDTO extends ResponseDTO {

	@JsonProperty("customerId")
	private String customerId;

	@JsonProperty("accountStatus")
	private String customerName;

	@JsonProperty("rewardPoints")
	private String rewardPoints;

	public RewardResponseDTO(String status, String customerId, String customerName, String rewardPoints) {
		super(status);
		this.customerId = customerId;
		this.customerName = customerName;
		this.rewardPoints = rewardPoints;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(String rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}