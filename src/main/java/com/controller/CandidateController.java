package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.entity.Candidate;
import com.entity.JobApplication;
import com.entity.JobDetails;
import com.service.CandidateService;
import com.service.JobApplicationService;
import com.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobService jobService;
    
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginCandidate", new Candidate());
        return "loginForm";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("loginCandidate") Candidate loginCandidate, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Candidate candidate = candidateService.authenticate(loginCandidate);
       
        if (candidate != null) {
            session.setAttribute("loggedInCandidate", candidate);
            model.addAttribute("loggedInCandidate", candidate);
            return "candidateDashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "loginForm";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/candidates/login";
    }
   

    @GetMapping("/create")
    public String createCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "createCandidateForm";
    }

    @PostMapping("/save")
    public String saveCandidateDetails(@ModelAttribute("candidate") Candidate candidate, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // Save candidate details to the session
            session.setAttribute("loggedInCandidate", candidate);

            candidateService.saveAddress(candidate.getAddress());
            candidateService.saveQualification(candidate.getQualification());
            Candidate savedCandidate = candidateService.saveCandidate(candidate);
            session.setAttribute("loggedInCandidate", savedCandidate);
            redirectAttributes.addFlashAttribute("successMessage", "Candidate saved successfully");
            return "redirect:/candidates/details";
        } catch (Exception e) {
            model.addAttribute("error", "Error saving candidate details");
            return "error";
        }
    }

    @GetMapping("/details")
    public String getCandidateDetails(Model model, HttpSession session) {
        try {
            // Retrieve the logged-in candidate from the session
            Candidate loggedInCandidate = (Candidate) session.getAttribute("loggedInCandidate");
            if (loggedInCandidate != null) {
                model.addAttribute("candidate", loggedInCandidate);
                return "candidateDetails";
            } else {
                return "redirect:/candidates/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Candidate not found");
            return "error";
        }
    }

    @GetMapping("/edit")
    public String editCandidateForm(Model model, HttpSession session) {
        try {
            // Retrieve the logged-in candidate from the session
            Candidate loggedInCandidate = (Candidate) session.getAttribute("loggedInCandidate");
            if (loggedInCandidate != null) {
                model.addAttribute("candidate", loggedInCandidate);
                return "editCandidateForm";
            } else {
                return "redirect:/candidates/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Candidate not found");
            return "error";
        }
    }

    @PostMapping("/update")
    public String updateCandidate(@ModelAttribute("candidate") Candidate candidate, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // Update candidate details in the session
            session.setAttribute("loggedInCandidate", candidate);

            Candidate updatedCandidate = candidateService.updateCandidate(candidate, candidate.getId());
			session.setAttribute("loggedInCandidate", updatedCandidate);
            redirectAttributes.addFlashAttribute("successMessage", "Candidate updated successfully");
            return "redirect:/candidates/details";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating candidate");
            return "error";
        }
    }

    @GetMapping("/apply")
    public String applyForJobs(Model model, HttpSession session) {
        // Check if candidate is logged in
        Candidate loggedInCandidate = (Candidate) session.getAttribute("loggedInCandidate");
        if (loggedInCandidate != null) {
            // Fetch all available jobs
            List<JobDetails> jobs = jobService.getAllActiveJobs();
            
            model.addAttribute("jobs", jobs);
            
            
            return "applyJobs";
        } else {
            // Redirect to login if candidate not logged in
            return "redirect:/candidates/login";
        }
    }

    @PostMapping("/apply")
    public String applyForJob(@RequestParam("jobId") Long jobId, HttpSession session, RedirectAttributes redirectAttributes) {
        Candidate _Candidate = (Candidate) session.getAttribute("loggedInCandidate");
        Candidate loggedInCandidate = candidateService.getCandidateById(_Candidate.getId());
        
            // Apply for the selected job
            JobApplication jobApplication = new JobApplication();
            jobApplication.setCandidate(loggedInCandidate);
            JobDetails jobDetails = jobService.getJobById(jobId).orElse(null);
            jobApplication.setJobDetails(jobDetails);
            
            redirectAttributes.addFlashAttribute("successMessage", "Applied for job successfully");
          
			try {
				if(jobDetails.getTotalVacancy()>0) {
					jobApplicationService.applyForJob(jobApplication);
					jobDetails.setTotalVacancy(jobDetails.getTotalVacancy()-1);
					jobService.updateJob(jobId, jobDetails);
				}
				
				if(jobDetails.getTotalVacancy()==0) {
					jobDetails.setStatus("InActive");
					jobService.updateJob(jobId, jobDetails);
				}
				return "redirect:/candidates/myJobs" ;
			}catch(Exception e) {
				return "redirect:/candidates/details" ;
			}
            
    }
        
    @GetMapping("/myJobs")
    public String getJobsAppliedByCandidate( Model model, HttpSession session) {
    	Candidate loggedInCandidate = (Candidate) session.getAttribute("loggedInCandidate");
        if (loggedInCandidate != null) {
            List<JobDetails> jobs = jobApplicationService.getJobsAppliedByCandidate(loggedInCandidate.getId());
            model.addAttribute("jobs", jobs);
            return "myJobList";
        } else {
            return "redirect:/candidates/login";
        }
    }

}
