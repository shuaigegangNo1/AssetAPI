package com.sgg.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sgg.rest.model.ApplicationUser;
import com.sgg.rest.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired 
	private UserRepository userRepository;

//    private ApplicationUserRepository applicationUserRepository;
//
//    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
//        this.applicationUserRepository = applicationUserRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	ApplicationUser user = userRepository.findByName(userId);
        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }
        return new org.springframework.security.core.userdetails.User(
        		user.getName(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}