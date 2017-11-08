package com.freelancerworld.service;

import com.freelancerworld.model.Profession;
import com.freelancerworld.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
	public User findUserByEmail(String email);
	public User findUserById(int id);
	public void saveUser(User user);
	public void addAdminPermissions(User user);
	public List<User> findAll();
	public User findUserByEmailAndPassword(String email, String password);
	public void updateProfession(User user, Set<Profession> professions);
}
