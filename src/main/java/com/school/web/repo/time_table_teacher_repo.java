package com.school.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.web.models.time_table_teacher;

public interface time_table_teacher_repo extends JpaRepository<time_table_teacher,String>
{
	time_table_teacher findByPhone(String phone);
	time_table_teacher findByName(String name);
}
