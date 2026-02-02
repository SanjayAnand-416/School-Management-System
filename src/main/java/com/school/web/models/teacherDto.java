package com.school.web.models;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class teacherDto 
{
	@NotEmpty(message="Email is required")
	@Email 
	private String email;
	
    @NotEmpty(message = "Phone number is required")
	private String phone;
	
	@NotEmpty(message="Subject is required")
	private String subject;
	
	@NotEmpty(message="Name is required")
	private String name;
	
	private LocalDate dob;
	
	@NotEmpty(message="Password is required")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
