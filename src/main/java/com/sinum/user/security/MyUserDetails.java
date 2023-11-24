package com.sinum.user.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sinum.user.entity.UserEntity;
import com.sinum.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {
	
	private final UserRepository userRepository;

	  @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    final UserEntity appUser = userRepository.findByUsername(username);

	    if (appUser == null) {
	      throw new UsernameNotFoundException("User '" + username + "' not found");
	    }

	    List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
	    listAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	    return org.springframework.security.core.userdetails.User//
	        .withUsername(username)
	        .password(appUser.getPassword())
	        .authorities(listAuthorities)
	        .accountExpired(false)
	        .accountLocked(false)
	        .credentialsExpired(false)
	        .disabled(false)
	        .build();
	  }
}
