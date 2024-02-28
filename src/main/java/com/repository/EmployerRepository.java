package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long>{

	@Query("SELECT e FROM Employer e WHERE e.emailId = ?1")
	Employer findByEmail(String emailId);

}
