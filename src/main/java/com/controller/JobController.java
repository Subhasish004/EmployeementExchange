//package com.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.entity.JobDetails;
//import com.service.EmployerService;
//import com.service.JobService;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/jobs/{emp_id}")
//public class JobController {
//
//    @Autowired
//    private JobService jobService;
//
//    @Autowired
//    private EmployerService employerService;
//
//    @GetMapping("/create")
//    public String createJobForm(@PathVariable Long emp_id, Model model) {
//        model.addAttribute("employerId", emp_id);
//        return "createJobForm";
//    }
//
//    @PostMapping("/create")
//    public String createJob(@PathVariable Long emp_id, @ModelAttribute("jobDetails") JobDetails jobDetails, Model model) {
//        try {
//            jobDetails.setEmployer(employerService.getEmployerById(emp_id).get());
//            JobDetails savedJob = jobService.createJob(jobDetails);
//            return "redirect:/jobs/" + emp_id;
//        } catch (Exception e) {
//            model.addAttribute("error", "Error creating job");
//            return "error";
//        }
//    }
//
//    @GetMapping
//    public String getAllJobs(@PathVariable Long emp_id, Model model) {
//        List<JobDetails> jobs = jobService.getAllJobs(emp_id);
//        model.addAttribute("jobs", jobs);
//        return "jobsList";
//    }
//
//    @GetMapping("/{id}")
//    public String getJobById(@PathVariable Long id, Model model) {
//        JobDetails job = jobService.getJobById(id).orElse(null);
//        model.addAttribute("job", job);
//        return "jobDetails";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editJobForm(@PathVariable Long id, Model model) {
//        JobDetails job = jobService.getJobById(id).orElse(null);
//        model.addAttribute("job", job);
//        return "editJobForm";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateJob(@PathVariable Long id, @ModelAttribute("jobDetails") JobDetails jobDetails, Model model) {
//        try {
//            JobDetails updatedJob = jobService.updateJob(id, jobDetails);
//            return "redirect:/jobs/" + jobDetails.getEmployer().getId();
//        } catch (Exception e) {
//            model.addAttribute("error", "Error updating job");
//            return "error";
//        }
//    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteJob(@PathVariable Long id, Model model) {
//        try {
//            jobService.deleteJob(id);
//            return "redirect:/jobs";
//        } catch (Exception e) {
//            model.addAttribute("error", "Error deleting job");
//            return "error";
//        }
//    }
//}
