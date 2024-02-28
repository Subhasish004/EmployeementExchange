package com.service;

import java.util.List;

import com.entity.Candidate;
import com.entity.JobApplication;
import com.entity.JobDetails;

public interface JobApplicationService {

	public JobApplication applyForJob(JobApplication jobApply);
	
	public List<Candidate> getAppliedCandidatesByJobId(Long jobId);
	
	public List<JobDetails> getJobsAppliedByCandidate(Long candidateId);
}
