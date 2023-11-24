package com.sinum.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinum.user.entity.UserEntity;
 
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	  boolean existsByUsername(String username);
	  
	  boolean existsByEmail(String email);

	  UserEntity findByUsername(String username);

	  @Transactional
	  void deleteByUsername(String username);
	  
}
