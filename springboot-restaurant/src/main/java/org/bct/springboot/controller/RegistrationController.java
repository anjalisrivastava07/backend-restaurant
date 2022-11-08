package org.bct.springboot.controller;

import org.bct.springboot.model.User;
import org.bct.springboot.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/restaurant")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		if(tempEmailId !=null && !"".equals(tempEmailId)){
			User userobj = registrationService.fetchUserByEmailId(tempEmailId);
			if(userobj != null) {
				throw  new Exception("User with "+tempEmailId+" is already exist");
			}
		}
		User users = null;
		users = registrationService.saveUser(user);
		return users;
	}
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception{
		String  tempEmailId	 = user.getEmailId();
		String tempPass = user.getPassword();
		User users = null;
		if(tempEmailId != null && tempPass!=null) {
			users = registrationService.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}
		if(users == null) {
			throw new Exception("Bad credentials");
		}
		return users;
		
	}
	

}
