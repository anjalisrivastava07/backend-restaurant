package org.bct.springboot.repository;

import org.bct.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<User, Integer>{
	public User findUserByEmailId(String email) ;
	public User findUserByEmailIdAndPassword(String email, String password) ;


}
