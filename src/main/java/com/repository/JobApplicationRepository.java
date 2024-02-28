package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Candidate;
import com.entity.JobApplication;
import com.entity.JobDetails;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

	@Query("SELECT ja.candidate FROM JobApplication ja WHERE ja.jobDetails.jobId = ?1")
	List<Candidate> getAppliedCandidatesByJobId(Long jobId);
	
	@Query("SELECT ja.jobDetails FROM JobApplication ja WHERE ja.candidate.id = ?1")
	List<JobDetails> getJobsAppliedByCandidate(Long candidateId);

}
