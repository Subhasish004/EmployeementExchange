package com.entity;


import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="candidate")
public class Candidate {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; 

	    private String candidateName;

	    private String emailId;

	    private String password;
	    
	  
	    @Temporal(TemporalType.DATE)
	    private Date dob; 

	    private Long contactNumber;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name="address_id")
	    private Address address;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name="qualification_id")
	    private Qualification qualification;

}
