package com.sgg.rest.service.impl;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sgg.rest.model.ApplicationUser;
import com.sgg.rest.model.UserQuery;
import com.sgg.rest.repository.UserRepository;
import com.sgg.rest.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Resource  
    UserRepository userRepository;
	@Override
	public Page<ApplicationUser> findUserNoCriteria(Integer page, Integer size) {
		  Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
	        return userRepository.findAll(pageable);  
	}

	@Override
	public Page<ApplicationUser> findUserCriteria(Integer page, Integer size, final UserQuery userQuery) {
	        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
	        Page<ApplicationUser> userPage = userRepository.findAll(new Specification<ApplicationUser>(){  
	            @Override  
	            public Predicate toPredicate(Root<ApplicationUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {  
//	                Predicate p1 = criteriaBuilder.equal(root.get("name").as(String.class), userQuery.getName());  
	                Predicate p2 = criteriaBuilder.like(root.get("email").as(String.class), "%"+ userQuery.getEmail() + "%");  
	                query.where(criteriaBuilder.and(p2));  
	                return query.getRestriction();  
	            }  
	        },pageable);  
	        return userPage;  
	}

}
