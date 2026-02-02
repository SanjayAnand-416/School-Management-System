package com.school.web.models;

import jakarta.validation.constraints.NotEmpty;


public class gradeDto 
{
	@NotEmpty(message="Name is required")
	private String name;
	
	@NotEmpty(message="Roll number is required")
	private String rollno;
	
	private int maths;
	
	private int science;
	
	private int english;
	
	private int history;
	
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	public int getBiology() {
		return biology;
	}
	public void setBiology(int biology) {
		this.biology = biology;
	}

	private int biology;
	
	private String exam;

	private String className;
	
	

	public String getClassName() {
	    return className;
	}
	public void setClassName(String className) {
	    this.className = className;
	}

	
	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
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

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}



}
