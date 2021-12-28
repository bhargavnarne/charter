package com.charter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "customer_name")
	private String customerName;

	private String customerPrimaryPhone;

	@Column(name = "Alternate_phone")
	private String customerAlternatePhone;

	@Column(name = "email")
	private String customerEmail;

	public String getId() {
		return id;
	}

	@Override
	protected void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPrimaryPhone() {
		return customerPrimaryPhone;
	}

	public void setCustomerPrimaryPhone(String customerPrimaryPhone) {
		this.customerPrimaryPhone = customerPrimaryPhone;
	}

	public String getCustomerAlternatePhone() {
		return customerAlternatePhone;
	}

	public void setCustomerAlternatePhone(String customerAlternatePhone) {
		this.customerAlternatePhone = customerAlternatePhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}