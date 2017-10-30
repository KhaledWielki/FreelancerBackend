package com.freelancerworld.service;

import com.freelancerworld.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void addAdminPermissions(User user);
}
