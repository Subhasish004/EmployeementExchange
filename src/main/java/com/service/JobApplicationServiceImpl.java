package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Candidate;
import com.entity.JobApplication;
import com.entity.JobDetails;
import com.repository.JobApplicationRepository;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Override
	public JobApplication applyForJob(JobApplication jobApply) {
		return jobApplicationRepository.save(jobApply);
	}

	@Override
	public List<Candidate> getAppliedCandidatesByJobId(Long jobId) {
		
		return jobApplicationRepository.getAppliedCandidatesByJobId(jobId);
	}

	@Override
	public List<JobDetails> getJobsAppliedByCandidate(Long candidateId) {
		
		return jobApplicationRepository.getJobsAppliedByCandidate(candidateId);
	}

	
}
