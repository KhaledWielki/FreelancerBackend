package com.freelancerworld.service;

import com.freelancerworld.model.User;

import java.util.List;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void addAdminPermissions(User user);
	public List<User> findAll();
}
