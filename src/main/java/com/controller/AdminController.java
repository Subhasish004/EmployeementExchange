package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Admin;
import com.entity.Candidate;
import com.entity.Employer;
import com.entity.JobDetails;
import com.service.AdminService;
import com.service.CandidateService;
import com.service.EmployerService;
import com.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JobService jobService;
    
    @Autowired
    private CandidateService candidateService;
    

    @Autowired
    private EmployerService employerService;
    
    @GetMapping("/index")
    public String home() {
    	return "index";    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminLogin";
    }

    //add logout
    @GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
    
    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("admin") Admin admin, HttpSession session, Model model) {
        Admin _admin = adminService.getAdminById(1L);
        if (_admin.getAdminName().equals(admin.getAdminName()) && _admin.getPassword().equals(admin.getPassword())) {
            session.setAttribute("admin", _admin);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "Invalid credentials");
        return "adminLogin";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            // Fetch and display relevant information on the admin dashboard
            List<JobDetails> allJobs = jobService.getAll();
            model.addAttribute("allJobs", allJobs);
            return "adminDashboard";
        } else {
            return "redirect:/admin/login";
        }
    }


    @PostMapping("/activateJob/{id}")
    public String activateJob(@PathVariable Long id) {
        JobDetails job = jobService.getJobById(id).get();
  
            job.setStatus("Active");
			jobService.updateJob(id, job);
        return "redirect:/admin/jobs";
    }
    @PostMapping("/deactivateJob/{id}")
    public String deActivateJob(@PathVariable Long id) {
        JobDetails job = jobService.getJobById(id).get();
  
            job.setStatus("InActive");
			jobService.updateJob(id, job);
        return "redirect:/admin/jobs";
    }
   
    
    @GetMapping("/jobs")
    public String  getAllJobs(Model model,HttpSession session) {
    	if (session.getAttribute("admin") != null) {
    	List<JobDetails> jobs = jobService.getAll();
    	model.addAttribute("jobs" , jobs);
    	return "adminJobList";} else {
            
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/employers")
    public String getAllEmployers(Model model, HttpSession session) {
     
        if (session.getAttribute("admin") != null) {
            List<Employer> employers = employerService.getAllEmployers();
            model.addAttribute("employers", employers);
            return "employersList";
        } else {
            
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/createEmployer")
    public String createEmployerForm(Model model, HttpSession session) {
        
        if (session.getAttribute("admin") != null) {
            model.addAttribute("employer", new Employer());
            return "createEmployerForm";
        } else {
            
            return "redirect:/admin/login";
        }
    }

    @PostMapping("/createEmployer")
    public String createEmployer(@ModelAttribute("employer") Employer employer) {
        try {
            employerService.createEmployer(employer);
            return "redirect:/admin/employers";
        } catch (Exception e) {
            return "redirect:/admin/employers";
        }
    }

    @GetMapping("/updateEmployer/{id}")
    public String updateEmployerForm(@PathVariable Long id, Model model, HttpSession session) {
    
        if (session.getAttribute("admin") != null) {
            Optional<Employer> employer = employerService.getEmployerById(id);
            if (employer.isPresent()) {
                model.addAttribute("employer", employer.get());
                return "updateEmployerForm";
            } else {
                return "redirect:/admin/employers";
            }
        } else {
            
            return "redirect:/admin/login";
        }
    }

    @PostMapping("/updateEmployer")
    public String updateEmployer( @ModelAttribute("employer") Employer employer) {
        try {
            employerService.updateEmployer(employer.getId(), employer);
            return "redirect:/admin/employers";
        } catch (Exception e) {
            return "redirect:/admin/employers";
        }
    }

    @GetMapping("/deleteEmployer/{id}")
    public String deleteEmployer(@PathVariable Long id, HttpSession session) {
      
        if (session.getAttribute("admin") != null) {
            try {
                employerService.deleteEmployer(id);
                return "redirect:/admin/employers";
            } catch (Exception e) {
                return "redirect:/admin/employers";
            }
        } else {
            
            return "redirect:/admin/login";
        }
    }
    @GetMapping("/candidates")
    public String getAllCandidates(Model model, HttpSession session) {
	    List<Candidate> candidates = candidateService.getAll();
	    model.addAttribute("candidates", candidates);
	    return "candidatesList";
    }
}
