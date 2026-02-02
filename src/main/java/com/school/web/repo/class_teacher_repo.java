package com.school.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.web.models.class_teacher;

public interface class_teacher_repo extends JpaRepository<class_teacher,String>
{
	class_teacher findByClass1(String class1);
	class_teacher findByTeacher(String classteacher);
}
