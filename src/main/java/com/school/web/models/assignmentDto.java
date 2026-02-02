package com.school.web.models;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class assignmentDto 
{
	@NotEmpty(message="Assignment Name is required")
	private String assignmentName;
	
	@NotEmpty(message="Assignment Description is required")
	private String assignmentDescription;
	
	@NotEmpty(message="Class Name is required")
	private String className;
	
	@NotNull(message = "End date is required")
	@FutureOrPresent(message = "End date must be today or in the future")
	private LocalDate enddate;
	
	@NotEmpty(message="Teacher Email is required")
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
