package com.school.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.web.models.assignmentDto;
import com.school.web.models.assignment_submission_table;
import com.school.web.models.assignment_table;
import com.school.web.models.email_service;
import com.school.web.models.student_table;
import com.school.web.models.teacher_table;
import com.school.web.models.time_table_teacher;
import com.school.web.repo.assignment_table_repo;
import com.school.web.repo.student_repo;
import com.school.web.repo.submission_table_repo;
import com.school.web.repo.teacher_repo;
import com.school.web.repo.time_table_teacher_repo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/home/assignments")
public class assignment_controller 
{
	@Autowired
	private teacher_repo tr;
	
	@Autowired
	private assignment_table_repo ar;
	
	@Autowired
	private time_table_teacher_repo ttr;
	
	@Autowired
	private email_service emailService;

	@Autowired
	private student_repo sr;
	
	@Autowired
	private submission_table_repo str;

	@GetMapping("/teacher_new_assignment")
	public String teacher_new_assignments(Model model,HttpSession session)
	{
		String email= (String)session.getAttribute("loggedinemail_teacher");
		teacher_table current_teacher=tr.findByEmail(email);
		model.addAttribute("current_teacher",current_teacher);
		time_table_teacher teacher_class=ttr.findByPhone(current_teacher.getPhone());
		model.addAttribute("teacher_class",teacher_class);
		model.addAttribute("assignmentDto",new assignmentDto());
		return "home/assignments/teacher_new_assignment"; 
		
	}	
	@PostMapping("/teacher_new_assignment")
	public String teacher_new_assignments_post(@Valid @ModelAttribute("assignmentDto") assignmentDto assignmentDto,BindingResult result,RedirectAttributes redirect,Model model,HttpSession session)
	{
		assignment_table existing_assignment=ar.findByAssignmentName(assignmentDto.getAssignmentName());
	    String email = (String) session.getAttribute("loggedinemail_teacher");
		if (result.hasErrors()) 
		{
	        teacher_table current_teacher = tr.findByEmail(email);
	        model.addAttribute("current_teacher", current_teacher);
	        model.addAttribute("error", "Please the check the end date");
			return "redirect:/home/assignments/teacher_new_assignment"; 
	    }
		if (existing_assignment!=null)
		{
			redirect.addFlashAttribute("error","Assignment with this name already exists");
			return "redirect:/home/assignments/teacher_assignments_details";
		}
	    assignmentDto.setTeacherEmail(email);
	    List<student_table> students = sr.findByClass1(assignmentDto.getClassName());
	    for (student_table student : students) {
	        emailService.sendEmailAssignment(
	            student.getEmail(),
	            assignmentDto.getAssignmentName(),
	            assignmentDto.getAssignmentDescription(),
	            assignmentDto.getEnddate().toString()
	            
	        );
	    }			
		assignment_table new_assignment=new assignment_table();
		
		new_assignment.setAssignmentName(assignmentDto.getAssignmentName());
		new_assignment.setAssignmentDescription(assignmentDto.getAssignmentDescription());
		new_assignment.setClassName(assignmentDto.getClassName());
		new_assignment.setEnddate(assignmentDto.getEnddate());
	    new_assignment.setTeacherEmail(assignmentDto.getTeacherEmail());

		ar.save(new_assignment);
		for (student_table student:students)
		{
			assignment_submission_table ns=new assignment_submission_table();
			ns.setAssignmentName(assignmentDto.getAssignmentName());
			ns.setClassName(assignmentDto.getClassName());
			ns.setStudentName(student.getName());
			ns.setEmail(student.getEmail());
			str.save(ns);
		}
		redirect.addFlashAttribute("success", "Assignment added successfully");
	    return "redirect:/home/assignments/teacher_assignments_details";
	}
	
	@GetMapping("/view/{id}")
	public String viewAssignmentDetails(@PathVariable("id") int id, Model model, HttpSession session) {
	    String email = (String) session.getAttribute("loggedinemail_teacher");
	    teacher_table current_teacher = tr.findByEmail(email);
	    model.addAttribute("current_teacher", current_teacher);

	    Optional<assignment_table> optionalAssignment = ar.findById(id);
	    if (optionalAssignment.isEmpty()) {
	        model.addAttribute("error", "Assignment not found.");
	        return "home/error";
	    }

	    assignment_table assignment = optionalAssignment.get();
	    model.addAttribute("assignment", assignment);

	    List<assignment_submission_table> st = str.findByAssignmentName(assignment.getAssignmentName());
	    model.addAttribute("st", st);

	    return "home/assignments/teacher_view_details";
	}

	
	@GetMapping("/remind")
	public String remind_student(@RequestParam("assignmentId") int assignmentId, RedirectAttributes redirect) {
	    Optional<assignment_table> optionalAssignment = ar.findById(assignmentId);
	    if (optionalAssignment.isEmpty()) {
	        redirect.addFlashAttribute("error", "Assignment not found.");
	        return "redirect:/home/assignments/teacher_assignments_details";
	    }

	    assignment_table assignment = optionalAssignment.get();
	    String assignmentName = assignment.getAssignmentName();

	    List<assignment_submission_table> not_submitted = str.findByAssignmentNameAndStatus(assignmentName, "Not Submitted");

	    for (assignment_submission_table student : not_submitted) {
	        emailService.sendEmailRemind(student.getEmail(), student.getAssignmentName());
	    }

	    redirect.addFlashAttribute("success", "Reminder sent successfully!");
	    return "redirect:/home/assignments/view/" + assignmentId;
	}

	@GetMapping("/view1/{id}")
	public String viewassignmentdetails(@PathVariable("id") int id, Model model, HttpSession session) {
	    String email = (String) session.getAttribute("loggedinemail_student");
	    student_table student = sr.findByEmail(email);
	    model.addAttribute("student", student);

	    assignment_table assignment = ar.findById(id).orElse(null);
	    if (assignment == null) {
	        model.addAttribute("error", "Assignment not found!");
	        return "home/error"; 
	    }

	    model.addAttribute("assignment", assignment);
	    return "home/assignments/student_view_details";
	}

	
	@GetMapping("/view_submission")
	public String view_submission(@RequestParam("studentId") int studentId,
	                              @RequestParam("assignmentId") int assignmentID,
	                              HttpSession session,
	                              Model model) {
	    
	    String email = (String) session.getAttribute("loggedinemail_teacher");
	    teacher_table current_teacher = tr.findByEmail(email);
	    model.addAttribute("current_teacher", current_teacher);

	    assignment_table existing_assignment = ar.findById(assignmentID).orElse(null);
	    if (existing_assignment == null) {
	        model.addAttribute("error", "Assignment not found!");
	        return "home/error"; 
	    }

	    assignment_submission_table existing_submission = str.findById(studentId).orElse(null);
	    if (existing_submission == null) {
	        model.addAttribute("error", "Submission not found!");
	        return "home/error";
	    }

	    model.addAttribute("assignment", existing_assignment);
	    model.addAttribute("submission", existing_submission);
	    return "home/assignments/teacher_view_submission";
	}

	@PostMapping("/submit_homework")
	public String submit_homework(HttpSession session,
	                              RedirectAttributes redirect,
	                              @RequestParam("file") MultipartFile file,
	                              @RequestParam("assignmentId") int assignmentId) {
	    String email = (String) session.getAttribute("loggedinemail_student");
	    
	    // Get assignment name from ID
	    assignment_table assignment = ar.findById(assignmentId).orElse(null);
	    if (assignment == null) {
	        redirect.addFlashAttribute("error", "Assignment not found.");
	        return "redirect:/home/student_homework";
	    }

	    String assignmentName = assignment.getAssignmentName();

	    List<assignment_submission_table> submissions = str.findByEmailAndAssignmentName(email, assignmentName);

	    if (submissions.isEmpty()) {
	        redirect.addFlashAttribute("error", "No submission record found.");
	        return "redirect:/home/student_homework";
	    }

	    // Assuming one matching record per student per assignment
	    assignment_submission_table submission = submissions.get(0);

	    if ("Submitted".equalsIgnoreCase(submission.getStatus())) {
	        redirect.addFlashAttribute("error", "Homework already submitted.");
	        return "redirect:/home/student_homework";
	    }

	    try {
	        submission.setStatus("Submitted");
	        submission.setFileName(file.getOriginalFilename());
	        submission.setFileType(file.getContentType());
	        submission.setFiledata(file.getBytes());
	        str.save(submission);
	        redirect.addFlashAttribute("success", "Homework successfully submitted!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirect.addFlashAttribute("error", "Submission failed.");
	    }

	    return "redirect:/home/student_homework";
	}


	@GetMapping("/send-email/{id}")
	public String sendEmailWithAttachment(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirect) {
	    assignment_submission_table submission = str.findById(id).orElse(null);
	    String teacherEmail = (String) session.getAttribute("loggedinemail_teacher");

	    if (submission == null || submission.getFiledata() == null) {
	        redirect.addFlashAttribute("error", "File not found or not submitted.");
	        return "redirect:/home/assignments/teacher_assignments_details"; // or appropriate path
	    }

	    try {
	        File tempfile = File.createTempFile("submission-", "-" + submission.getFileName());
	        try (FileOutputStream fos = new FileOutputStream(tempfile)) {
	            fos.write(submission.getFiledata());
	        }

	        emailService.sendEmailWithAttachment(
	            teacherEmail,
	            "Assignment submitted: " + submission.getAssignmentName(),
	            "Dear Teacher,<br><br>Here is the submitted assignment file from student: <strong>"
	                    + submission.getStudentName() + "</strong><br><br>Regards,<br>School Portal",
	            tempfile
	        );

	        tempfile.deleteOnExit();
	        redirect.addFlashAttribute("success", "Email sent to teacher successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirect.addFlashAttribute("error", "Failed to send email.");
	    }

	    return "redirect:/home/assignments/view/" + submission.getId(); // or wherever you're coming from
	}

	
}