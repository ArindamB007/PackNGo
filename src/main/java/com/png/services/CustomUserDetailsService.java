package com.png.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.png.data.entity.User;
import com.png.data.entity.UserContext;
import com.png.data.repository.UserRepository;
import com.png.data.entity.Role;;;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	private UserContext userContext;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) {
		User user =null;
		user = userRepository.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException(String.format("Username and or password invalid. Please try again!", email));
		userContext = new UserContext();
		userContext.setUserDetails(user);
		userContext.setGrantedAuthorities(getAuthorities(user));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}

	private Set<GrantedAuthority> getAuthorities (User user){
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Role role:user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return grantedAuthorities;
	}

	public UserContext getUserContext() {
		return userContext;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
}
