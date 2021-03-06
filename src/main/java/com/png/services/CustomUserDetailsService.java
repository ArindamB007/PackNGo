package com.png.services;

import com.png.data.dto.user.UserContext;
import com.png.data.entity.Role;
import com.png.data.entity.User;
import com.png.data.repository.UserRepository;
import com.png.exception.EmailNotVerifiedException;
import com.png.menu.Menu;
import com.png.menu.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

;
;

@Service
@Configurable
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	private UserContext userContext;
	
	private User user;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) {
		user = null;
		this.user = userRepository.findByEmail(email);
		if (this.user == null)
			throw new UsernameNotFoundException(String.format("Username and or password invalid. Please try again!", email));
		userContext = new UserContext();
		userContext.setUserRoles(getRoles(this.user));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}

	public User getUserByUserContext(UserContext userContext){
		return userRepository.findByEmail(userContext.getEmail());
	}
	
	private ArrayList<String> getRoles (User user){
		ArrayList<String> userRoles = new ArrayList<String>();
		for(Role role:user.getRoles()){
			userRoles.add(role.getName());
		}
		return userRoles;
	}

	private Set<GrantedAuthority> getAuthorities (User user){
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Role role:user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return grantedAuthorities;
	}

	public UserContext getUserContext() {
        if (userContext == null)
            userContext = new UserContext();
		userContext.setUserDetails(this.user);
		return this.userContext;
	}

	public Collection<Menu> getUserMenu(Long id_user) throws IOException{
		if (id_user == null)
			return MenuMapper.getMenu(null, null);
		else{
			User anUser = userRepository.findByIdUser(id_user);
			return MenuMapper.getMenu(anUser.getRoles(),anUser.getFirstName());
		}
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
	
	public void updateLastLoginTimeStamp(Timestamp lastLoginTimestamp){
		this.user.setLastLoginTimestamp(lastLoginTimestamp);
	}

	public void updateLastLogOffTimeStamp(Timestamp lastLogOffTimestamp){
		this.user.setLastLogOffTimestamp(lastLogOffTimestamp);
	}

    public void resetForgotPasswordData() {
        this.user.setPasswordLinkSentTimestamp(null);
        this.user.setForgotPasswordCode(null);
        this.user.setPasswordLinkValidUptoTimestamp(null);
    }

	public void saveUser(){
        this.user = userRepository.save(this.user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
