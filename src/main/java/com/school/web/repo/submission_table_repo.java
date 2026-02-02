package com.school.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import com.school.web.models.assignment_submission_table;

public interface submission_table_repo extends JpaRepository<assignment_submission_table,Integer> 
{
	List <assignment_submission_table> findByAssignmentName(String assignmentName);
	List <assignment_submission_table> findByStatus(String status);
	List<assignment_submission_table> findByAssignmentNameAndStatus(String assignmentName, String status);
	List<assignment_submission_table> findByEmailAndAssignmentName(String email, String assignmentName);
	Optional<assignment_submission_table> findById(Integer id);
	List<assignment_submission_table> findByEmail(String email);
}
