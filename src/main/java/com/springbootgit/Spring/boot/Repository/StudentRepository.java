package com.springbootgit.Spring.boot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootgit.Spring.boot.Students.Department;
import com.springbootgit.Spring.boot.Students.Students;


public interface StudentRepository extends JpaRepository<Students, Long> {


	boolean existsByFathername(String fathername);



	boolean existsByStudentname(String studname);



	boolean existsByEmail(String email);



	Students findByEmail(String email);



	



	List<Students> findAllBystudentdept(Department department);

}


