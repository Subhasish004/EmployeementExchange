//package com.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.entity.Candidate;
//import com.entity.JobApplication;
//import com.entity.JobDetails;
//import com.service.JobApplicationService;
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
//@RequestMapping("/api/apply")
//public class JobApplicationController {
//	
//	@Autowired
//	private JobApplicationService jobApplicationService;
//	
//	@PostMapping
//	public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobApplication) {
//		try {
//		return new ResponseEntity<>(jobApplicationService.applyForJob(jobApplication),HttpStatus.CREATED);
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//	}
//	
//	@GetMapping("/candidate/{id}")
//	public ResponseEntity<List<Candidate>> getCandicatesByJob(@PathVariable Long id) {
//		try {
//			jobApplicationService.getAppliedCandidatesByJobId(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//	}
//	
//	@GetMapping("/jobs/{candidateName}")
//	public ResponseEntity<List<JobDetails>> getJobAppliedByCandidate(@PathVariable Long id) {
//		try {
//		return new ResponseEntity<>(jobApplicationService.getJobsAppliedByCandidate(id),HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//}
