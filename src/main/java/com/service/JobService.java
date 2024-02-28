package com.service;
import java.util.List;
import java.util.Optional;

import com.entity.JobDetails;

public interface JobService {
	 List<JobDetails> getAll();
	 List<JobDetails> getAllJobs(Long emp_id);
	 Optional<JobDetails> getJobById(Long id);
	 JobDetails createJob(JobDetails job);
	 JobDetails updateJob(Long id, JobDetails jobDetails);
	 void deleteJob(Long id);
	List<JobDetails> getAllActiveJobs();
		
}
