package com.school.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.school.web.models.GradeId;
import com.school.web.models.grade_table;
import java.util.*;


public interface grade_repo extends JpaRepository<grade_table, GradeId> {
	Optional<grade_table> findById(GradeId id);
    List<grade_table> findByIdRollno(String rollno);

    Optional<grade_table> findByIdRollnoAndIdExam(String rollno, String exam);
}
