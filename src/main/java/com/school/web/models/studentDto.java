package com.school.web.models;

import java.time.LocalDate;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class studentDto 
{
	@NotEmpty(message="Email is required")
	@Email 
	private String email;
	
    @NotEmpty(message = "Roll number is required")
	private String rollno;
	
	@NotEmpty(message="Class is required")
	private String class1;
	
	@NotEmpty(message="Name is required")
	private String name;
	
	@NotEmpty(message="Password is required")
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private LocalDate dob;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
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
}
