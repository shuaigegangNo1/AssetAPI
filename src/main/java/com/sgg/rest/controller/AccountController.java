package com.sgg.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.rest.model.Account;
import com.sgg.rest.model.ApplicationUser;
import com.sgg.rest.repository.AccountRepository;
import com.sgg.rest.repository.UserRepository;

@RestController // @Controller+@ResponseBody 
@RequestMapping("/account")
//@SuppressWarnings("all")
public class AccountController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	//curl 'localhost:8080/demo/addAccount?accountNo=99999&userId=1'
	@GetMapping(path="/addAccount") // Map ONLY GET Requests
	public String addAccount (@RequestParam String accountNo, @RequestParam int userId) {
		Account a = new Account();
		 ApplicationUser u = userRepository.findOne(userId);
		a.setAccountNo(accountNo);
		a.setUser(u);
//		n.setEmail(email);
		accountRepository.save(a);
		return "Saved account";
	}
	//curl 'localhost:8080/account/allAccount?userId=1'
	@GetMapping(path="/allAccount")
	public Iterable<Account> getAllAccounts(@RequestParam int userId) {
		// This returns a JSON or XML with the users
		ApplicationUser u = userRepository.findOne(userId);
		return accountRepository.findAccountsByUser(u);
	}
}
