package com.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="job")
public class JobDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long jobId;
	@Column(nullable=false)
	private String jobName;
	@Column(nullable=false)
	private String specification;
	@Column(nullable=false)
	private String jobRole;
	@Column(nullable=false)
	private String companyName;
	@Column(nullable=false)
	private String eligibility;
	@Column(nullable=false)
	private double salary;
	@Column(nullable=false)
	private String location;
	@Column(nullable=false)
	private int totalVacancy;
	@Column(nullable=false)
	private String status="InActive";
	

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employer_id")
	private Employer employer;  
	
}
