package com.school.web.models;

import java.time.LocalDate;


import jakarta.persistence.*;

@Entity
@Table (name="student")
public class student_table 
{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true,nullable=false)
	private String email;
	
	@Column(unique=true,nullable=false)
	private String password;
	
	@Column(unique=true,nullable=false)
	private String name;
	
	@Column(unique=true,nullable=false)
	private String rollno;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	private String class1;
	
	
	private LocalDate dob;	
}
