package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Employer;
import com.entity.JobDetails;
import com.service.EmployerService;
import com.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobService jobService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("employer", new Employer());
        return "employerLogin";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/employer/login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("employer") Employer employer, HttpSession session, Model model) {
		Employer logInEmployer = employerService.authenticateEmployer(employer);
		
		if (logInEmployer != null) {
            // If authenticated, save user details to the session
        	
            session.setAttribute("loggedInEmployer", logInEmployer);
            return "redirect:/employer/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "employerLogin";
        }
    }

    @GetMapping("/dashboard")
    public String employerDashboard(Model model, HttpSession session) {
        // Check if employer is logged in
        if (session.getAttribute("loggedInEmployer") != null) {
        	
        	model.addAttribute("loggedInEmployer", (Employer) session.getAttribute("loggedInEmployer"));
            return "employerDashboard";
        } else {
            // Redirect to login if employer not logged in
            return "redirect:/employer/login";
        }
    }

    @GetMapping("/postJob")
    public String postJobForm(Model model, HttpSession session) {
        // Check if employer is logged in
        if (session.getAttribute("loggedInEmployer") != null) {
            model.addAttribute("jobDetails", new JobDetails());
            return "postJobForm";
        } else {
            // Redirect to login if employer not logged in
            return "redirect:/employer/login";
        }
    }

    @PostMapping("/postJob")
    public String postJob(@ModelAttribute("jobDetails") JobDetails jobDetails, HttpSession session, Model model) {
        // Check if employer is logged in
        if (session.getAttribute("loggedInEmployer") != null) {
           
                Employer loggedInEmployer = (Employer) session.getAttribute("loggedInEmployer");
                Employer managedEmployer = employerService.getEmployerById(loggedInEmployer.getId()).orElse(null);

                jobDetails.setEmployer(managedEmployer);

                // Save the job details
                jobDetails.setStatus("InActive");
                JobDetails savedJob = jobService.createJob(jobDetails);

                model.addAttribute("successMessage", "Job posted successfully");
                return "redirect:/employer/jobs/"+savedJob.getJobId();
            
        } else {
            // Redirect to login if employer not logged in
            return "redirect:/employer/login";
        }
    }

    @GetMapping("/jobs/{id}")
    public String getJobDetails(@PathVariable Long id, Model model) {
        // Retrieve job details by ID (add your logic)
        JobDetails jobDetails = jobService.getJobById(id).orElse(null);
        if (jobDetails != null) {
            model.addAttribute("jobDetails", jobDetails);
            return "jobDetails";
        } else {
            return "jobNotFound";
        }
    }
    @GetMapping("/jobList/{id}")
    public String  getAllJobsByEmployerId(Model model, @PathVariable Long id) {
    	List<JobDetails> jobs = jobService.getAllJobs(id);
    	model.addAttribute("jobs" , jobs);
    	return "jobList";
    }
    
}
