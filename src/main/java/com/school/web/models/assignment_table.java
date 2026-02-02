package com.school.web.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table (name="assignment")
public class assignment_table 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(unique=true,nullable=true)
	private String assignmentName;
	
	@Column(unique=false,nullable=true)
	private String assignmentDescription;
	
	@Column(unique=false,nullable=true)
	private String className;
	
	@Column(unique=false,nullable=true)
	private LocalDate enddate;
	
	@Column(unique=false,nullable=true)
	private String teacherEmail;


	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getAssignmentDescription() {
		return assignmentDescription;
	}

	public void setAssignmentDescription(String assignmentDescription) {
		this.assignmentDescription = assignmentDescription;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}
}
