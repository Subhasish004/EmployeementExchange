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
@Table(name="qualification")
public class Qualification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String courseName;
	@Column(nullable=false)
	private String instituteName;
	@Column(nullable=false)
	private int yearOfPassing;
	@Column(nullable=false)
	private int fullMark;
	@Column(nullable=false)
	private double markSecured;
	@Column(nullable=false)
	private double percentage;

//	private Candidate personalDetails;

	
}
