package com.myproject.auth;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final List<User> users = Arrays.asList(			
				new User(1, "Sachin", encoder.encode("abcd1234"), "USER"),
				new User(2, "Harshit", encoder.encode("def456"), "ADMIN")
			);
		for(User user: users) {
			if(user.getUserName().equals(username)) {
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
				return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
			}
		}
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

	
}
