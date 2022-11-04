package com.security.orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.orm.model.User;
import com.security.orm.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Integer saveUser(User user) {
		
		User u = userRepository.save(user);
		return u.getUid();
	}

}
