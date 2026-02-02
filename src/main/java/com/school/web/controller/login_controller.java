package com.school.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.web.models.studentDto;
import com.school.web.models.student_table;
import com.school.web.models.teacherDto;
import com.school.web.models.teacher_table;
import com.school.web.repo.student_repo;
import com.school.web.repo.teacher_repo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class login_controller 
{
	@Autowired
	private student_repo sr;
	
	@Autowired
	private teacher_repo tr;
	
	@GetMapping("/login")
	public String login(Model model)
	{
		studentDto student=new studentDto();
		model.addAttribute("student",student);
		return "login/login";
	}
	
	@GetMapping("/new_acc")
	public String newaccount()
	{
		return "login/new_acc";
	}
	
	@GetMapping("/new_student")
	public String newstudent(Model model)
	{
		studentDto register_student=new studentDto();
		model.addAttribute("register_student",register_student);
		return "login/new_student";
	}
	
	@GetMapping("/new_teacher")
	public String newteacher(Model model)
	{
		teacherDto register_teacher=new teacherDto();
		model.addAttribute("register_teacher",register_teacher);
		return "login/new_teacher";
	}
	
	@GetMapping("/forgot_password")
	public String reset_password()
	{
		return "/login/forgot_password";
	}
	
	@GetMapping("/reset_student")
	public String reset_student(Model model)
	{
		model.addAttribute("rs",new studentDto());
		return "/login/reset_student";
	}
	
	@GetMapping("/reset_teacher")
	public String reset_teacher(Model model)
	{
		model.addAttribute("rt",new teacherDto());
		return "/login/reset_teacher";
	}
	
	@PostMapping("/login")
	public String login_post(@ModelAttribute("student") studentDto student,
	                         @RequestParam String role,
	                         RedirectAttributes redirect,HttpSession session) {

	    if ("student".equals(role)) {
	        student_table existing = sr.findByEmail(student.getEmail());
	        if (existing != null && existing.getPassword().equals(student.getPassword())) {
	            redirect.addFlashAttribute("success", "Student login successful!");
	            session.setAttribute("loggedinemail_student", existing.getEmail());
	            return "redirect:/home/student_home";
	        } else {
	            redirect.addFlashAttribute("error", "Invalid student credentials.");
	            return "redirect:/login";
	        }
	    } else if ("teacher".equals(role)) {
	        teacher_table teacher = tr.findByEmail(student.getEmail());
	        if (teacher != null && teacher.getPassword().equals(student.getPassword())) {
	            redirect.addFlashAttribute("success", "Teacher login successful!");
	            session.setAttribute("loggedinemail_teacher",teacher.getEmail());
	            return "redirect:/home/teacher_home";
	        } else {
	            redirect.addFlashAttribute("error", "Invalid teacher credentials.");
	            return "redirect:/login";
	        }
	    }

	    redirect.addFlashAttribute("error", "Invalid role selected.");
	    return "redirect:/login";
	}
	@PostMapping("/new_student")
	public String register_new_student(@Valid @ModelAttribute("register_student") studentDto register_student,
										BindingResult result,RedirectAttributes redirect,Model model)
	{
		if (result.hasErrors()) 
		{
		        model.addAttribute("register_student", register_student);
		        return "login/new_student"; 
		}
		student_table existing_email=sr.findByEmail(register_student.getEmail());
		student_table existing_roll=sr.findByRollno(register_student.getRollno());
		if (existing_email==null && existing_roll==null)
		{
			redirect.addFlashAttribute("success","Successfully created");
			student_table ns=new student_table();
			ns.setEmail(register_student.getEmail());
			ns.setClass1(register_student.getClass1());
			ns.setDob(register_student.getDob());
			ns.setPassword(register_student.getPassword());
			ns.setName(register_student.getName());
			ns.setRollno(register_student.getRollno());
			sr.save(ns);
            return "redirect:/login";
		}
		else
		{
			redirect.addFlashAttribute("error","Already the account and roll number is present try again with someother account");
            return "redirect:/new_student";
		}
	}
	
	@PostMapping("/new_teacher")
	public String register_new_teacher(@Valid @ModelAttribute("register_teacher") teacherDto register_teacher,
										BindingResult result,RedirectAttributes redirect,Model model)
	{
		if(result.hasErrors())
		{
	        model.addAttribute("register_teacher", register_teacher);
			return "login/new_teacher";
		}
		teacher_table existing_email=tr.findByEmail(register_teacher.getEmail());
		teacher_table existing_phone=tr.findByPhone(register_teacher.getPhone());
		if(existing_email==null && existing_phone==null)
		{
			redirect.addFlashAttribute("success","Successfully created");
			teacher_table nt=new teacher_table();
			nt.setEmail(register_teacher.getEmail());
			nt.setPassword(register_teacher.getPassword());
			nt.setName(register_teacher.getName());
			nt.setDob(register_teacher.getDob());
			nt.setPhone(register_teacher.getPhone());
			nt.setSubject(register_teacher.getSubject());
			tr.save(nt);
            return "redirect:/login";
		}
		else
		{
			redirect.addFlashAttribute("error","Already the account is present try again with someother account or create new");
            return "redirect:/new_teacher";
		}
	}
	
	@PostMapping("/reset_student")
	public String reset_student_post(@ModelAttribute("rs")studentDto rs,RedirectAttributes redirect)
	{
		student_table existing_email=sr.findByEmail(rs.getEmail());
		if(existing_email == null)
		{
			redirect.addFlashAttribute("error","No such account is present try again with someother account or create new");
			return "redirect:/reset_student";
		}
		else
		{
			if(existing_email.getRollno().equals(rs.getRollno()))
			{
				redirect.addFlashAttribute("success","Password changed successfully");
				existing_email.setPassword(rs.getPassword());
				sr.save(existing_email);
	            return "redirect:/login";
			}
			else
			{
				redirect.addFlashAttribute("error","Roll number doesnt match please recheck");
				return "redirect:/reset_student";
			}
		}
	}
	
	@PostMapping("/reset_teacher")
	public String reset_teacher_post(@ModelAttribute("rt")teacherDto rt,RedirectAttributes redirect)
	{
		teacher_table existing_email=tr.findByEmail(rt.getEmail());
		if(existing_email == null)
		{
			redirect.addFlashAttribute("error","No such account is present try again with someother account or create new");
			return "redirect:/reset_teacher";
		}
		else
		{
			if(existing_email.getPhone().equals(rt.getPhone()))
			{
				redirect.addFlashAttribute("success","Password changed successfully");
				existing_email.setPassword(rt.getPassword());
				tr.save(existing_email);
	            return "redirect:/login";
			}
			else
			{
				redirect.addFlashAttribute("error","Phone number doesnt match please recheck");
				return "redirect:/reset_teacher";
			}
		}
	}
}
