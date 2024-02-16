package com.mentasBoutique.mentasUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mentasBoutique.mentasUser.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
@Query("SELECT u FROM User u WHERE u.email=?1")
User findByEmail(String email);
	

	
@Query("SELECT u FROM User u WHERE u.password=?1")
User findUserPassword(String password);
	

}
