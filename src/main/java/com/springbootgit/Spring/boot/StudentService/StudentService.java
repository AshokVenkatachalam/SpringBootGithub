package com.springbootgit.Spring.boot.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springbootgit.Spring.boot.Repository.MarksRepository;
import com.springbootgit.Spring.boot.Repository.StudentRepository;
import com.springbootgit.Spring.boot.Students.Department;
import com.springbootgit.Spring.boot.Students.StudentMarks;
import com.springbootgit.Spring.boot.Students.Students;

@Service
public class StudentService {

	private final StudentRepository studentrepository;

	private final MarksRepository studentmarks;

	@Autowired
	public StudentService(StudentRepository studentrepository, MarksRepository studentmarks) {
		super();
		this.studentrepository = studentrepository;
		this.studentmarks = studentmarks;
	}

	public ResponseEntity<String> register(String fathername, String studname, Department dept, String age,
			String email, String password) {

//		List<Students> checkstudent=studentrepository.findAllByFathername(fathername);
		boolean checkstudent = studentrepository.existsByEmail(email);

//		List<Students> checkstudent1=studentrepository.findAllByStudentname(studname);

		if (checkstudent == false) {

			Students newstudent = new Students();
			newstudent.setFathername(fathername);
			newstudent.setStudentname(studname);
			newstudent.setStudentage(age);
			newstudent.setStudentdept(dept);
			newstudent.setEmail(email);
			newstudent.setPassword(password);

			Students savestudent = studentrepository.save(newstudent);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Resistered Successfully");

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Exist");

		}
	}

	public ResponseEntity<String> login(String email, String password) {

		Students checkstudent = studentrepository.findByEmail(email);

		if (checkstudent.getPassword().equals(password)) {

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Success");

		} else {

			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Email or Password Wrong");

		}

	}

	public List<StudentMarks> viewMarks(Long id) {
		
		List<StudentMarks> marks= studentmarks.findAllBystudent_id(id);
		
		return marks;

	}



}
