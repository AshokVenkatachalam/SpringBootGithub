package com.springbootgit.Spring.boot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootgit.Spring.boot.model.Department;
import com.springbootgit.Spring.boot.model.Students;


public interface StudentRepository extends JpaRepository<Students, Long> {


	boolean existsByFathername(String fathername);



	boolean existsByStudentname(String studname);



	boolean existsByEmail(String email);



	Students findByEmail(String email);



	



	List<Students> findAllBystudentdept(Department department);

}


