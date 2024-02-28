package com.service;
import java.util.List;
import java.util.Optional;

import com.entity.Employer;

public interface EmployerService {

   List<Employer> getAllEmployers();
        
    public Optional<Employer> getEmployerById(Long id);
        

    public Employer createEmployer(Employer employer);
        
    public Employer updateEmployer(Long id, Employer updatedEmployer);
        
        
    public void deleteEmployer(Long id);

	Employer authenticateEmployer(Employer employer);
}
