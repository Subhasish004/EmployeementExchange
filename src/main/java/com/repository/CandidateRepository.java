package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Candidate;

public interface CandidateRepository  extends JpaRepository<Candidate, Long> {
	
	@Query("SELECT c FROM Candidate c WHERE c.emailId = ?1")
	Candidate findByEmailId(String emailId);

}
