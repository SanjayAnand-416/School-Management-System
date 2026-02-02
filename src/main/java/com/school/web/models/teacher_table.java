package com.school.web.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table (name="teacher")
public class teacher_table 
{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true,nullable=false)
	private String email;
	
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Column(unique=true,nullable=false)
	private String password;
	
	@Column(unique=true,nullable=false)
	private String name;
	
	@Column(unique=true,nullable=false)
	private String phone;
	
	private String subject;
	
	private LocalDate dob;	
}
