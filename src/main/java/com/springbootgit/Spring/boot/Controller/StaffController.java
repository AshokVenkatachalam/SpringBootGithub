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
import com.springbootgit.Spring.boot.StudentService.StaffService;
import com.springbootgit.Spring.boot.StudentService.StudentService;
import com.springbootgit.Spring.boot.Students.Email;
import com.springbootgit.Spring.boot.Students.Staffs;
import com.springbootgit.Spring.boot.Students.StudentMarks;
import com.springbootgit.Spring.boot.Students.Students;

@RestController
@RequestMapping("api/staff/")
public class StaffController {

	private final StaffService staffservice;

	private final StudentService studentservice;

	@Autowired
	public StaffController(StaffService staffservice, StudentService studentservice) {
		super();
		this.staffservice = staffservice;
		this.studentservice = studentservice;
	}

	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody Staffs staf) {

		return staffservice.register(staf);
	}

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody Staffs staf) {

		return staffservice.login(staf);
	}

	@GetMapping("viewstudents")
	public ResponseEntity<List<Students>> viewStudents() {

		return studentservice.viewStudents();
	}

	@PostMapping("addmarks/{id}")
	public ResponseEntity<String> addMarks( @RequestBody StudentMarks studentmarks,@PathVariable Long id) {
			
		return staffservice.addMarks(id,studentmarks);
	}
	
	@PostMapping("email/send")
	public ResponseEntity<String> emailNotification(@RequestBody Email email) {
		
		return staffservice.emailNotification(email);	
	}
	
	

}
