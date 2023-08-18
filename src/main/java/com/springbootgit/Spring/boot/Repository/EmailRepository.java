package com.springbootgit.Spring.boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootgit.Spring.boot.Students.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
