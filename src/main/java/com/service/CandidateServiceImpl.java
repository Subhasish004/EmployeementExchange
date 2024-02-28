package com.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Address;
import com.entity.Candidate;
import com.entity.Qualification;
import com.repository.AddressRepository;
import com.repository.CandidateRepository;
import com.repository.QualificationRepository;

@Service
public class CandidateServiceImpl implements CandidateService {
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private QualificationRepository qualificationRepository;

	@Override
	public Candidate saveCandidate(Candidate candidate) {
		
		return candidateRepository.save(candidate);
	}

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Qualification saveQualification(Qualification qualification) {
		return qualificationRepository.save(qualification);
	}

	@Override
	public Candidate getCandidateById(Long candidateId) {
		
		return candidateRepository.findById(candidateId).get();
	}

	@Override
	public Address getAddressById(Long id) {
		
		return addressRepository.findById(id).get();
	}

	@Override
	public Qualification getQualificationById(Long id) {
		
		return qualificationRepository.findById(id).get();
	}



	@Override
	public Candidate updateCandidate(Candidate candidate, Long id) {
		Candidate existingCandidate=candidateRepository.findById(id).get();
		existingCandidate.setCandidateName(candidate.getCandidateName());
		existingCandidate.setPassword(candidate.getPassword());
		existingCandidate.setEmailId(candidate.getEmailId());
		existingCandidate.setDob(candidate.getDob());
		existingCandidate.setContactNumber(candidate.getContactNumber());
		return candidateRepository.save(existingCandidate);
	}

	@Override
	public Address updateAddress(Address address, Long id) {
		Address existAddress=addressRepository.findById(id).get();
		existAddress.setPlotNumber(address.getPlotNumber());
		existAddress.setLaneNumber(address.getLaneNumber());
		existAddress.setStreetName(address.getStreetName());
		existAddress.setPinCode(address.getPinCode());
		existAddress.setCity(address.getCity());
		existAddress.setState(address.getCity());
		existAddress.setCountry(address.getCountry());
		return addressRepository.save(existAddress);
	}

	@Override
	public Qualification updateQualification(Qualification qualification, Long id) {
		Qualification existingQualification=qualificationRepository.findById(id).get();
		existingQualification.setCourseName(qualification.getCourseName());
		existingQualification.setInstituteName(qualification.getInstituteName());
		existingQualification.setYearOfPassing(qualification.getYearOfPassing());
		existingQualification.setFullMark(qualification.getFullMark());
		existingQualification.setMarkSecured(qualification.getMarkSecured());
		return qualificationRepository.save(existingQualification);
	}

	@Override
	public void deleteCandidate(Long candidateId) {
		candidateRepository.deleteById(candidateId);
		
	}

	@Override
	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
		
	}

	@Override
	public void deleteQualification(Long id) {
		qualificationRepository.deleteById(id);
		
	}

	@Override
	public Candidate authenticate(Candidate loginCandidate) {
		Candidate candidate=candidateRepository.findByEmailId(loginCandidate.getEmailId());
		if(candidate!=null && candidate.getPassword().equals(loginCandidate.getPassword())) {
			return candidate;
		}
		return null;
	}

	@Override
	public List<Candidate> getAll() {
		
		return candidateRepository.findAll();
	}


	
}


