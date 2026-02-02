package com.school.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.web.models.assignment_table;
import com.school.web.models.class_teacher;
import com.school.web.models.grade_table;
import com.school.web.models.student_table;
import com.school.web.repo.assignment_table_repo;
import com.school.web.repo.class_teacher_repo;
import com.school.web.repo.grade_repo;
import com.school.web.repo.student_repo;
import java.util.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class student_home_controller 
{
	@Autowired
	private grade_repo gr;
	
	@Autowired
	private student_repo sr;
	
	@Autowired
	private class_teacher_repo ctr; 
	
	@Autowired
	private assignment_table_repo ar;
	
	@GetMapping("/student_home")
	public String student_home(Model model,HttpSession session)
	{
	    String email = (String) session.getAttribute("loggedinemail_student");
		student_table student=sr.findByEmail(email);
		model.addAttribute("student",student);
		return "home/student_home";
	}
	
	@GetMapping("/student_profile")
	public String student_profile(Model model,HttpSession session)
	{
		String email=(String) session.getAttribute("loggedinemail_student");
		student_table profile=sr.findByEmail(email);
		model.addAttribute("profile",profile);
		student_table student=sr.findByEmail(email);
		model.addAttribute("student",student);
		return "home/student_profile";
	}
	
	@GetMapping("/grade")
	public String student_grade(Model model, HttpSession session) {
	    String email = (String) session.getAttribute("loggedinemail_student");
	    student_table student = sr.findByEmail(email);
	    List<grade_table> grades = gr.findByIdRollno(student.getRollno());
	    model.addAttribute("gradeList", grades); 
	    model.addAttribute("student", student);
	    return "home/grade";
	}


	@GetMapping("/student_class")
	public String student_class_get(Model model,HttpSession session)
	{
		String email=(String) session.getAttribute("loggedinemail_student");
		student_table student=sr.findByEmail(email);
		String student_class=student.getClass1();
		List <student_table> class_students=sr.findByClass1(student_class);
		model.addAttribute("class_students",class_students);
		model.addAttribute("student",student);
		class_teacher class_t=ctr.findByClass1(student_class);
		model.addAttribute("class_t",class_t);
		return "home/student_class";
	}
	
	@GetMapping("/student_homework")
	public String studentHomework(Model model, HttpSession session) {
	    String email = (String) session.getAttribute("loggedinemail_student");
	    student_table student = sr.findByEmail(email);
	    model.addAttribute("student", student);

	    String student_class = student.getClass1();
	    List<assignment_table> student_assignment = ar.findByClassName(student_class);
	    model.addAttribute("student_assignment", student_assignment);
	    return "home/student_homework";
	}


	@GetMapping("/student_news")
	public String student_news(Model model ,HttpSession session)
	{
		String email=(String) session.getAttribute("loggedinemail_student");
		student_table student=sr.findByEmail(email);
		model.addAttribute("student",student);
		return "home/student_news";
	}
	

	
	@GetMapping("/student_event")
	public String student_event(Model model ,HttpSession session)
	{
		String email=(String) session.getAttribute("loggedinemail_student");
		student_table student=sr.findByEmail(email);
		model.addAttribute("student",student);
		return "home/student_event";
	}
	@GetMapping("/student_project")
	public String student_project(Model model,HttpSession session)
	{
	    String email = (String) session.getAttribute("loggedinemail_student");
		student_table student=sr.findByEmail(email);
		model.addAttribute("student",student);
		return "home/student_project";
	}

}