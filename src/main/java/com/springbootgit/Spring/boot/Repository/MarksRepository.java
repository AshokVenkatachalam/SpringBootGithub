package com.springbootgit.Spring.boot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootgit.Spring.boot.model.StudentMarks;

public interface MarksRepository extends JpaRepository<StudentMarks, Long> {


	List<StudentMarks> findAllBystudent_id(Long id);

}
