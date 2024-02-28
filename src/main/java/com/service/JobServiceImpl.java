package com.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.JobDetails;
import com.repository.JobRepository;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<JobDetails> getAllJobs(Long id) {
		return jobRepository.findAllJobsById(id);
	}

	@Override
	public Optional<JobDetails> getJobById(Long id) {
		return jobRepository.findById(id);
	}

	@Override
	public JobDetails createJob(JobDetails job) {
		return jobRepository.save(job);
	}

	@Override
	public JobDetails updateJob(Long id, JobDetails jobDetails) {
		Optional<JobDetails> Job=getJobById(id);
		if(Job.isPresent()) {
			JobDetails updatedJob=Job.get();
		updatedJob.setJobName(jobDetails.getJobName());
		updatedJob.setSpecification(jobDetails.getSpecification());
		updatedJob.setJobRole(jobDetails.getJobRole());
		updatedJob.setCompanyName(jobDetails.getCompanyName());
		updatedJob.setEligibility(jobDetails.getEligibility());
		updatedJob.setSalary(jobDetails.getSalary());
		updatedJob.setLocation(jobDetails.getLocation());
		updatedJob.setTotalVacancy(jobDetails.getTotalVacancy());
		return jobRepository.save(updatedJob);
		}
		return null;
		
	}

	@Override
	public void deleteJob(Long id) {
		jobRepository.deleteById(id);
	}

	@Override
	public List<JobDetails> getAll() {
		
		return jobRepository.findAll();
	}

	@Override
	public List<JobDetails> getAllActiveJobs() {
		return jobRepository.findAllActiveJobs();
		
	}


	
	
	
}
