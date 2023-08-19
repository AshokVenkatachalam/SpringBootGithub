package com.springbootgit.Spring.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emails")
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fromemail")
	private String fromemail;
	@Column(name = "todepartment")
	@Enumerated(EnumType.STRING)
	private Department todepartment;
	@Column(name = "subject")
	private String subject;
	@Column(name = "content")
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFromemail() {
		return fromemail;
	}
	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}
	public Department getTodepartment() {
		return todepartment;
	}
	public void setTodepartment(Department todepartment) {
		this.todepartment = todepartment;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Email(Long id, String fromemail, Department todepartment, String subject, String content) {
		super();
		this.id = id;
		this.fromemail = fromemail;
		this.todepartment = todepartment;
		this.subject = subject;
		this.content = content;
	}
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
