package com.png.auth.service;

import com.png.data.entity.User;
import com.png.data.repository.RoleRepository;
import com.png.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.trimUserData();
		//Assign only user role to the newly signed up user
		user.setRoles(new HashSet<>(roleRepository.findByName("ROLE_USER")));
		user.setCreatedTimestamp(new Timestamp(new java.util.Date().getTime()));
		user.setUpdatedTimestamp(new Timestamp(new java.util.Date().getTime()));
		userRepository.save(user);
	}
	@Override
	public User findByUsername(String email) {
		return userRepository.findByEmail(email);
	}
	
	

}
