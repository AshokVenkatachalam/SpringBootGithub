package com.springbootgit.Spring.boot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "studentdetails")
public class Students {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "studentname")
	private String studentname;
	@Column(name = "studentage")
	private String studentage;
	@Column(name = "studentdept")
	@Enumerated(EnumType.STRING)
	private Department studentdept;
	@Column(name = "fathername")
	private String fathername;
	@Column(name =  "email")
	private String email;
	@Column(name =  "password")
	private String password;
	
	 @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	    private List<StudentMarks> marks;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getStudentage() {
		return studentage;
	}
	public void setStudentage(String studentage) {
		this.studentage = studentage;
	}
	public Department getStudentdept() {
		return studentdept;
	}
	public void setStudentdept(Department studentdept) {
		this.studentdept = studentdept;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Students(long id, String studentname, String studentage, Department studentdept, String fathername,
			String email, String password) {
		super();
		this.id = id;
		this.studentname = studentname;
		this.studentage = studentage;
		this.studentdept = studentdept;
		this.fathername = fathername;
		this.email = email;
		this.password = password;
	}
	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	
	

}
