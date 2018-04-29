package com.sgg.rest.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.sgg.rest.model.Account;
import com.sgg.rest.model.ApplicationUser;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	 @Transactional
	    List<Account> findAccountsByUser(ApplicationUser u);

}
