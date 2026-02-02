package com.school.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.web.models.assignment_table;
import java.util.*;

public interface assignment_table_repo extends JpaRepository<assignment_table,Integer>
{
	public assignment_table findByAssignmentName(String assignmentName);
	List <assignment_table> findByTeacherEmail(String teacherEmail);
	List <assignment_table> findByClassName(String className);
	Optional <assignment_table> findById(int id);
}
