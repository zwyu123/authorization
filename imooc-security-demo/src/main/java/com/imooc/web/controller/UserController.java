package com.imooc.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;

@RestController
@RequestMapping
public class UserController {
	
	@PostMapping
	public User created(@Valid @RequestBody User user) {
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
//				FieldError fieldError = (FieldError)error;
//				String messageString = fieldError.getField() + " " + error.getDefaultMessage();
				System.out.println(error.getDefaultMessage());
			});
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void DeleteMapping(@PathVariable String id) {
		System.out.println(id);
	}
	
	@GetMapping("/user")
	@JsonView(User.UserSimpleView.class)
	public List<User> query(UserQueryCondition condition){
		List<User> users = new ArrayList<>();
		users.add(new User());
		return users;
	}
	

	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id) {
		
		throw new UserNotExistException(id);
		
//		User user = new User();
//		user.setUsername("tom");
//		return user;
	}

}
