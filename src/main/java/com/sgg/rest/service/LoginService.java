package com.sgg.rest.service;

//import org.slf4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sgg.rest.model.ApplicationUser;
import com.sgg.rest.repository.UserRepository;
@Service
public class LoginService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encode;
	private static final Logger LOGGER = Logger.getLogger(LoginService.class);
//	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	public ApplicationUser Login(String name, String password) {
		LOGGER.info(">>>> start enter Login>>>>");
		ApplicationUser user = userRepository.findByName(name);
		if (user!=null) {
			Boolean match = encode.matches(password, user.getPassword());
			if (match) {
				return user;
			}
		}
		return null;
	}
	public ApplicationUser loadUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByName(userId);
	}
}
