package com.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Employer;
import com.repository.EmployerRepository;

@Service
public class EmployerServiceImpl implements EmployerService{
    @Autowired
    private EmployerRepository employerRepository;

	@Override
	public List<Employer> getAllEmployers() {
		return employerRepository.findAll();
	}

	@Override
	public Optional<Employer> getEmployerById(Long id) {
		return employerRepository.findById(id);
	}

	@Override
	public Employer createEmployer(Employer employer) {
		return employerRepository.save(employer);
	}

	@Override
	public Employer updateEmployer(Long id, Employer updatedEmployer) {
		Optional<Employer> employer = getEmployerById(id);
		Employer existingEmployer = employer.get();
        existingEmployer.setEmployerName(updatedEmployer.getEmployerName());
        existingEmployer.setCompanyName(updatedEmployer.getCompanyName());
        existingEmployer.setDesignation(updatedEmployer.getDesignation());
        existingEmployer.setPassword(updatedEmployer.getPassword());
        existingEmployer.setEmailId(updatedEmployer.getEmailId());
       
        Employer savedEmploye = employerRepository.save(existingEmployer);
        
        return savedEmploye;
	}

	@Override
	public void deleteEmployer(Long id) {
		employerRepository.deleteById(id);
	}

	@Override
	public Employer authenticateEmployer(Employer loginEmployer) {
		Employer employer = employerRepository.findByEmail(loginEmployer.getEmailId());
		
		if(employer != null && employer.getPassword().equals(loginEmployer.getPassword())) {
			return employer;
		}else {
			return null;
			
		}
	}

    
}