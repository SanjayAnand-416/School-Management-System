package com.school.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.school.web.models.student_table;
import java.util.List;


public interface student_repo extends JpaRepository<student_table,Integer>
{
	public student_table findById(int id);
	public student_table findByRollno(String rollno);
	public student_table findByEmailAndPassword(String email,String password);
	public student_table findByEmail(String email);
	public student_table findByPassword(String password);
    List<student_table> findByClass1(String class1);

}
