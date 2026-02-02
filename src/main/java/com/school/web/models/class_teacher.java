package com.school.web.models;

import jakarta.persistence.*;

@Entity
@Table(name="class_teacher")
public class class_teacher 
{
	@Id
	@Column(unique=true,nullable=false)
	private String class1;
	
	@Column(unique=true,nullable=false)
	private String teacher;

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	public String getTeacher() { 
		return teacher;
	}

	public void setTeacher(String teacher) { 
		this.teacher = teacher;
	}
}
