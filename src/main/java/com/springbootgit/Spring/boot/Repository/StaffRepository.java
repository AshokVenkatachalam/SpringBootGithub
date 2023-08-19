package com.springbootgit.Spring.boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootgit.Spring.boot.model.Staffs;

public interface StaffRepository  extends JpaRepository<Staffs, Long>{

	boolean existsByEmail(String email);

	Staffs findByEmail(String email);

}
