package com.springbootgit.Spring.boot.Students;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "studentmarks")
public class StudentMarks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "english")
	private int English;
	@Column(name = "maths")
	private int Maths;
	@Column(name = "physics")
	private int Physics;
	@Column(name = "eg")
	private int EG;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Students student;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEnglish() {
		return English;
	}

	public void setEnglish(int english) {
		English = english;
	}

	public int getMaths() {
		return Maths;
	}

	public void setMaths(int maths) {
		Maths = maths;
	}

	public int getPhysics() {
		return Physics;
	}

	public void setPhysics(int physics) {
		Physics = physics;
	}

	public int getEG() {
		return EG;
	}

	public void setEG(int eG) {
		EG = eG;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public StudentMarks(long id, int english, int maths, int physics, int eG, Students student) {
		super();
		this.id = id;
		English = english;
		Maths = maths;
		Physics = physics;
		EG = eG;
		this.student = student;
	}

	public StudentMarks() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
