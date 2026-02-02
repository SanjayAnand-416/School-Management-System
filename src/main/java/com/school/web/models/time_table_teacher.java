package com.school.web.models;

import jakarta.persistence.*;

@Entity
@Table(name="tt_teacher")
public class time_table_teacher
{
	@Id
	private String phone;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(unique=false,nullable=false)
	private String name;
	
	@Column(unique=false,nullable=false)
	private String period1;
	
	@Column(unique=false,nullable=false)
	private String period2;
	
	@Column(unique=false,nullable=false)
	private String period3;
	
	@Column(unique=false,nullable=false)
	private String period4;
	
	@Column(unique=false,nullable=false)
	private String period5;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriod1() {
		return period1;
	}

	public void setPeriod1(String period1) {
		this.period1 = period1;
	}

	public String getPeriod2() {
		return period2;
	}

	public void setPeriod2(String period2) {
		this.period2 = period2;
	}

	public String getPeriod3() {
		return period3;
	}

	public void setPeriod3(String period3) {
		this.period3 = period3;
	}

	public String getPeriod4() {
		return period4;
	}

	public void setPeriod4(String period4) {
		this.period4 = period4;
	}

	public String getPeriod5() {
		return period5;
	}

	public void setPeriod5(String period5) {
		this.period5 = period5;
	}

}
