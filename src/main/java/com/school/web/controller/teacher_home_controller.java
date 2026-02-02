package com.school.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.web.models.GradeId;
import com.school.web.models.assignmentDto;
import com.school.web.models.assignment_table;
import com.school.web.models.class_teacher;
import com.school.web.models.gradeDto;
import com.school.web.models.grade_table;
import com.school.web.models.student_table;
import com.school.web.models.teacher_table;
import com.school.web.models.time_table_teacher;
import com.school.web.repo.assignment_table_repo;
import com.school.web.repo.class_teacher_repo;
import com.school.web.repo.grade_repo;
import com.school.web.repo.student_repo;
import com.school.web.repo.teacher_repo;
import com.school.web.repo.time_table_teacher_repo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class teacher_home_controller 
{
	@Autowired
	private time_table_teacher_repo ttr;
	
	@Autowired
	private teacher_repo tr;
	
	@Autowired
	private class_teacher_repo ctr;
	
	@Autowired
	private student_repo sr;
	
	@Autowired
	private grade_repo gr;
	
	@Autowired
	private assignment_table_repo ar;
	@GetMapping("/teacher_home")
	public String teacher_home_get(Model model,HttpSession session)
	{
		String email=(String) session.getAttribute("loggedinemail_teacher");
		teacher_table current_teacher=tr.findByEmail(email);
		model.addAttribute("current_teacher",current_teacher);
		time_table_teacher teacher_tt=ttr.findByPhone(current_teacher.getPhone());
		model.addAttribute("teacher_tt",teacher_tt);
		return "home/teacher_home";
	}
	
	@GetMapping("/teacher_profile")
	public String teacher_profile_get(Model model,HttpSession session)
	{
		String email=(String) session.getAttribute("loggedinemail_teacher");
		teacher_table current_teacher=tr.findByEmail(email);
		model.addAttribute("current_teacher",current_teacher);
		time_table_teacher teacher_tt=ttr.findByPhone(current_teacher.getPhone());
		model.addAttribute("teacher_tt",teacher_tt);
		class_teacher teacher_ct=ctr.findByTeacher(current_teacher.getName());
		model.addAttribute("teacher_ct",teacher_ct);
		return "home/teacher_profile";
	}
	
	@GetMapping("/teacher_grade")
    public String teacherGradeGet(Model model, HttpSession session) {
        String email = (String) session.getAttribute("loggedinemail_teacher");
        teacher_table currentTeacher = tr.findByEmail(email);
        model.addAttribute("current_teacher", currentTeacher);

        time_table_teacher teacher_tt = ttr.findByPhone(currentTeacher.getPhone());
        model.addAttribute("teacher_tt", teacher_tt);

        gradeDto gradeDto = new gradeDto();
        model.addAttribute("gradeDto", gradeDto);

        String teacherSubject = currentTeacher.getSubject(); 
        model.addAttribute("teacherSubject", teacherSubject);

        return "home/teacher_grade";
    }

    @GetMapping("/grade_class_loader")
    public String gradeClassLoader(@ModelAttribute("gradeDto") gradeDto gradeDto, RedirectAttributes redirect) {
        String selectedClass = gradeDto.getClassName();
        String selectedExam = gradeDto.getExam();

        List<student_table> classStudents = sr.findByClass1(selectedClass);
        redirect.addFlashAttribute("studentList", classStudents);
        redirect.addFlashAttribute("selectedClass", selectedClass);
        redirect.addFlashAttribute("selectedExam", selectedExam);

        return "redirect:/home/teacher_grade";
    }

    @PostMapping("/submitMarks")
    public String submitMarks(@RequestParam String className,
                              @RequestParam String examName,
                              @RequestParam String teacherSubject,
                              @RequestParam MultiValueMap<String, String> formData,
                              RedirectAttributes redirect) {

        List<student_table> students = sr.findByClass1(className);

        System.out.println("Form Data Received: " + formData);

        for (student_table student : students) {
            String paramName = teacherSubject.trim().toLowerCase() + "_" + student.getRollno();
            String marksStr = formData.getFirst(paramName);

            System.out.println("Checking field: " + paramName + " => " + marksStr);

            if (marksStr != null && !marksStr.isEmpty()) {
                try {
                    int marks = Integer.parseInt(marksStr.trim());

                    GradeId gradeId = new GradeId(student.getRollno(), examName.trim());
                    Optional<grade_table> gradeOpt = gr.findById(gradeId);

                    grade_table grade;
                    if (gradeOpt.isPresent()) {
                        grade = gradeOpt.get();
                        System.out.println("Updating existing grade record for: " + gradeId.getRollno());
                    } else {
                        grade = new grade_table();
                        grade.setId(gradeId);
                        grade.setName(student.getName());
                        System.out.println("Creating new grade record for: " + gradeId.getRollno());
                    }

                    String subject = teacherSubject.trim().toLowerCase();
                    System.out.println("Normalized subject: '" + subject + "' → Setting marks: " + marks);
                    switch (subject) {
                        case "math":
                        case "mathematics":
                            grade.setMaths(marks);
                            break;
                        case "science":
                            grade.setScience(marks);
                            break;
                        case "english":
                            grade.setEnglish(marks);
                            break;
                        case "biology":
                            grade.setBiology(marks);
                            break;
                        case "history":
                            grade.setHistory(marks);
                            break;
                    }


                    gr.save(grade);

                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid number format for: " + paramName);
                }
            }
        }

        redirect.addFlashAttribute("success", "Marks submitted successfully!");
        return "redirect:/home/teacher_grade";
    }
    @GetMapping("/assignments/teacher_assignments_details")
    public String teacher_assignments_details_get(Model model,HttpSession session)
    {
    	String email = (String) session.getAttribute("loggedinemail_teacher");
    	teacher_table current_teacher = tr.findByEmail(email);
    	model.addAttribute("current_teacher", current_teacher);
    	List <assignment_table> assignment=ar.findByTeacherEmail(email);
    	model.addAttribute("assignment",assignment);
    	assignmentDto assignmentDto = new assignmentDto(); 
    	model.addAttribute("assignmentDto", assignmentDto);
    	return "/home/assignments/teacher_assignments_details";
    	
    }


}