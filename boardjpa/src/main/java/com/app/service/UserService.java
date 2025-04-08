package com.app.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.BoardUser;
import com.app.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public BoardUser create (String username, String email, String password) {
		BoardUser user = new BoardUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
	
	public BoardUser getUser(String username) {
		Optional<BoardUser> _boardUser = userRepository.findByUsername(username);
		if (_boardUser.isPresent()) {
			return _boardUser.get();
		} else {
			throw new DataNotFoundException("BoardUser not found");
		}
	}
}
