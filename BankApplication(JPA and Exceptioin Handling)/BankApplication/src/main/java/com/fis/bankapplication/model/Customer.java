package com.fis.bankapplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long id;

	@NotBlank(message = "Name is required")
	@Column(name = "full_name")
	private String name;

	@NotNull(message="Mobile number is required")
	@Pattern(regexp = "^[0-9]{10}", message ="Mobile number must be 10 digits")
	@Column(name = "mobile_number")
	private String mobile;	

	@Email(message = "Please enter a valid email")
	@Column(name = "email_address")
	private String email;
	
	@NotBlank(message = "Aadhar Card number is required")
	@Pattern(regexp = "^[2-9]{1}[0-9]{11}$", message ="Aadhar number must be 12 digits")
	@Column(name = "aadhar_number")
	private String aadharCardNumber;

	@NotNull(message="DOB is required")
	@Past(message = "Please enter a valid DOB")
	@Column(name = "date_of_birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateOfBirth;

	@NotBlank(message = "The residential address is required")
	@Column(name = "residential_address")
	private String residentialAddress;

	@NotBlank(message = "The permanent address is required")
	@Column(name = "permanent_address")
	private String permanentAddress;

	@NotBlank(message = "The occupational details is required")
	@Column(name = "occupational_details")
	private String occupationalDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getOccupationalDetails() {
		return occupationalDetails;
	}

	public void setOccupationalDetails(String occupationalDetails) {
		this.occupationalDetails = occupationalDetails;
	}
}