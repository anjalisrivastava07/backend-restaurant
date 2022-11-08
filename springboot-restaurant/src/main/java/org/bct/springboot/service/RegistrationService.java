package org.bct.springboot.service;

import org.bct.springboot.model.User;
import org.bct.springboot.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository; 
	
	public User saveUser(User user) {
		return registrationRepository.save(user); 
	}
	public User fetchUserByEmailId(String email) {
		return registrationRepository.findUserByEmailId(email);
	}
	public User fetchUserByEmailIdAndPassword(String email, String password) {
		return registrationRepository.findUserByEmailIdAndPassword(email, password);
	}

}
