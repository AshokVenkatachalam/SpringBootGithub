package com.springbootgit.Spring.boot.StudentService;

import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springbootgit.Spring.boot.Repository.EmailRepository;
import com.springbootgit.Spring.boot.Repository.MarksRepository;
import com.springbootgit.Spring.boot.Repository.StaffRepository;
import com.springbootgit.Spring.boot.Repository.StudentRepository;
import com.springbootgit.Spring.boot.model.Email;
import com.springbootgit.Spring.boot.model.Staffs;
import com.springbootgit.Spring.boot.model.StudentMarks;
import com.springbootgit.Spring.boot.model.Students;

@Service
public class StaffService {

	private final StaffRepository staffrepository;

	private final StudentRepository studentrepository;

	private final MarksRepository marksrepository;

	private final EmailRepository emailrepository;

	

	@Autowired
	public StaffService(StaffRepository staffrepository, StudentRepository studentrepository,
			MarksRepository marksrepository, EmailRepository emailrepository) {
		super();
		this.staffrepository = staffrepository;
		this.studentrepository = studentrepository;
		this.marksrepository = marksrepository;
		this.emailrepository = emailrepository;

	}

//	public void register(Staffs staf ) {
//
//		
//	}

	public ResponseEntity<String> login(Staffs staf) {

		Staffs checkstaf = staffrepository.findByEmail(staf.getEmail());

		if (checkstaf != null) {

			if (checkstaf.getPassword().equals(staf.getPassword())) {

				return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Success");

			} else {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Password");
			}

		} else {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Not Found");
		}

	}

	public void viewStudents() {

	}

	public ResponseEntity<String> addMarks(Long id, StudentMarks studentmarks1) {

		Students checkstudent = studentrepository.findById(id).get();

//		Students getstudent=new Students();
//		getstudent.setEmail(checkstudent.getEmail());
//		getstudent.setFathername(checkstudent.getFathername());
//		getstudent.setPassword(checkstudent.getPassword());
//		getstudent.setStudentage(checkstudent.getStudentage());
//		getstudent.setStudentdept(checkstudent.getStudentdept());
//		getstudent.setStudentname(checkstudent.getStudentname());

//		System.out.println(EG);

		StudentMarks studentmarks = new StudentMarks();
		studentmarks.setStudent(checkstudent);
		studentmarks.setEG(studentmarks1.getEG());
		studentmarks.setEnglish(studentmarks1.getEnglish());
		studentmarks.setMaths(studentmarks1.getMaths());
		studentmarks.setPhysics(studentmarks1.getPhysics());

		marksrepository.save(studentmarks);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Mark Added");

	}

	public ResponseEntity<String> register(Staffs staf) {

		boolean checkstaff = staffrepository.existsByEmail(staf.getEmail());

		if (checkstaff == false) {

			Staffs newstaf = new Staffs();
			newstaf.setEmail(staf.getEmail());
			newstaf.setPassword(staf.getPassword());
			newstaf.setUsername(staf.getUsername());
			newstaf.setDepartment(staf.getDepartment());

			staffrepository.save(newstaf);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Registration Success");

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Exists");
		}

	}

	public ResponseEntity<String> emailNotification(Email email) {

		Email setemail = new Email();
		setemail.setContent(email.getContent());
		setemail.setFromemail(email.getFromemail());
		setemail.setSubject(email.getSubject());
		setemail.setTodepartment(email.getTodepartment());

		List<Students> liststuednts = studentrepository.findAllBystudentdept(email.getTodepartment());

//		System.out.println(department.toString());
		sendEmail(email.getFromemail(), email.getContent(), email.getSubject(), email.getTodepartment().toString(),
				liststuednts);

		emailrepository.save(setemail);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Email Send");

	}

	

	public static void sendEmail(String from, String content, String subject, String department,
			List<Students> students) {

		System.out.println(from);
		System.out.println(content);
		System.out.println(subject);
		System.out.println(department);

		final String username = "javafabhost2021@gmail.com";
		final String password = "rzrpppusjjrdlkqa";

		// Email properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Create a session with authentication
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			// Add multiple recipients
//          String[] recipients = {
//              "recipient1@example.com",
//              "recipient2@example.com",
//              "recipient3@example.com"
			// Add more recipients as needed
//          };
			InternetAddress[] addresses = new InternetAddress[students.size()];
			for (int i = 0; i < students.size(); i++) {
				addresses[i] = new InternetAddress(students.get(i).getEmail());
			}
			message.setRecipients(Message.RecipientType.TO, addresses);

			message.setSubject(subject);
			message.setText(content);

			// Send the message
			Transport.send(message);

			System.out.println("Group email sent successfully.");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.err.println("Error sending group email: " + e.getMessage());
		}
	}

	

}
