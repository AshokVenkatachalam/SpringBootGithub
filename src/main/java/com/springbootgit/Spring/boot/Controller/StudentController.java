package com.springbootgit.Spring.boot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootgit.Spring.boot.StudentService.StudentService;
import com.springbootgit.Spring.boot.Students.StudentMarks;
import com.springbootgit.Spring.boot.Students.Students;

@RestController
@RequestMapping("/api/stud/")
public class StudentController {

	private final StudentService studentservice;

	@Autowired
	public StudentController(StudentService studentservice) {
		super();
		this.studentservice = studentservice;
	}

	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody Students student) {

	return	studentservice.register(student.getFathername(),student.getStudentname(),student.getStudentdept(),student.getStudentage(),student.getEmail(),student.getPassword());
	}

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody Students student) {
		
		return	studentservice.login(student.getEmail(),student.getPassword());
	}

	@GetMapping("marks/{id}")
	public List<StudentMarks> viewMarks(@PathVariable Long id) {

		return studentservice.viewMarks(id);
	}



}
