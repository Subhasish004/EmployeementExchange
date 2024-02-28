package com.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.JobDetails;

@Repository
public interface JobRepository extends JpaRepository<JobDetails, Long> {

	@Query("SELECT j FROM JobDetails j WHERE j.employer.id = :id")
	List<JobDetails> findAllJobsById(Long id);

	@Query("SELECT j FROM JobDetails j WHERE j.status = 'Active'")
	List<JobDetails> findAllActiveJobs();
	
}

