package com.school.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.web.models.teacher_table;


public interface teacher_repo extends JpaRepository<teacher_table,Integer>
{
	public teacher_table findById(int id);
	public teacher_table findByEmail(String email);
	public teacher_table findByPhone(String phone);
}
