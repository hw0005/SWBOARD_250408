package com.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.repository.BoardUserRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService{
	private final BoardUserRepository boardUserRepository;
	
	public BoardUserDetailsService (BoardUserRepository boardUserRepository) {
		this.boardUserRepository = boardUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return boardUserRepository.getByUid(username);
	}
	
	
}
