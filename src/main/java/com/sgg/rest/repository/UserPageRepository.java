package com.sgg.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sgg.rest.model.ApplicationUser;

public interface UserPageRepository extends PagingAndSortingRepository<ApplicationUser,Integer> {
//	Page<ApplicationUser> findByDeletedFalse(Pageable pageable);
//    @Query(value="select user from application_user user where user.name=?1")
//    Page<ApplicationUser> findByStudent(String name, Pageable pageable);
}
