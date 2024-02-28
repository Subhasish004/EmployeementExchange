package com.service;
import java.util.List;

import com.entity.Address;
import com.entity.Candidate;
import com.entity.Qualification;

public interface CandidateService {
	List<Candidate> getAll();
	Candidate saveCandidate(Candidate candidate);
	Address saveAddress(Address address);
	Qualification saveQualification(Qualification qualification);
	Candidate getCandidateById(Long candidateId);
	Address getAddressById(Long id);
	Qualification getQualificationById(Long id);
	Candidate updateCandidate(Candidate candidate, Long id);
	Address updateAddress(Address address, Long id);
	Qualification updateQualification(Qualification qualification, Long id);
	void deleteCandidate(Long candidateId);
	void deleteAddress(Long id);
	void deleteQualification(Long id);
	Candidate authenticate(Candidate loginCandidate);
}


