package com.sgg.rest.service;

import org.springframework.data.domain.Page;

import com.sgg.rest.model.ApplicationUser;
import com.sgg.rest.model.UserQuery;

public interface UserService {
//	@Autowired
//	private UserRepository userRepository;
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
//    public ApplicationUser findOne(Integer id){
//        return userRepository.findOne(id);
//    }
    Page<ApplicationUser> findUserNoCriteria(Integer page,Integer size);  
    Page<ApplicationUser> findUserCriteria(Integer page,Integer size,UserQuery userQuery);  
}
