package com.springbootgit.Spring.boot.StudentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springbootgit.Spring.boot.Repository.MarksRepository;
import com.springbootgit.Spring.boot.Repository.StudentRepository;
import com.springbootgit.Spring.boot.model.StudentMarks;
import com.springbootgit.Spring.boot.model.Students;

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

	public ResponseEntity<String> register(Students student) {

		boolean checkstudent = studentrepository.existsByEmail(student.getEmail());
		if (checkstudent == false) {
			Students newstudent = new Students();
			newstudent.setFathername(student.getFathername());
			newstudent.setStudentname(student.getStudentname());
			newstudent.setStudentage(student.getStudentage());
			newstudent.setStudentdept(student.getStudentdept());
			newstudent.setEmail(student.getEmail());
			newstudent.setPassword(student.getPassword());

			 studentrepository.save(newstudent);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Resistered Successfully");

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Exist");

		}
	}

	public ResponseEntity<String> login(Students student) {

		Students checkstudent = studentrepository.findByEmail(student.getEmail());

		if (checkstudent.getPassword().equals(student.getPassword())) {

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Success");

		} else {

			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Email or Password Wrong");

		}

	}

	public List<StudentMarks> viewMarks(Long id) {

		List<StudentMarks> marks = studentmarks.findAllBystudent_id(id);

		return marks;

	}

	public ResponseEntity<String> updateProfile(Long id, Students student) {

		Optional<Students> checkstudent = studentrepository.findById(id);

		if (checkstudent.isPresent()) {
			Students exist = checkstudent.get();
			exist.setEmail(student.getEmail());
			exist.setFathername(student.getFathername());
			exist.setPassword(student.getPassword());
			exist.setStudentage(student.getStudentage());

			studentrepository.save(exist);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Profile Updated");

		} else {

			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("User Not Exist");
		}

	}
	
	public ResponseEntity<List<Students>> viewStudents() {
		
	List<Students> liststudents=	studentrepository.findAll();
	
	return ResponseEntity.ok(liststudents);
		
	}

}
