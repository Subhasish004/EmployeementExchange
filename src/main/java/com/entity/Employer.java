package com.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="employer")
public class Employer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String employerName;
	@Column(nullable=false)
	private String designation;
	@Column(nullable=false)
	private String companyName;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false ,unique = true)
	private String emailId;
	
	
}
