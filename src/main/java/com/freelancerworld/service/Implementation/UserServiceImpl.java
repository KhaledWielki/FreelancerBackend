package com.freelancerworld.service.Implementation;

import java.util.*;

import com.freelancerworld.model.Profession;
import com.freelancerworld.model.Request;
import com.freelancerworld.repository.ProfessionRepository;
import com.freelancerworld.repository.RequestRepository;
import com.freelancerworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.freelancerworld.model.Role;
import com.freelancerworld.model.User;
import com.freelancerworld.repository.RoleRepository;
import com.freelancerworld.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
	private ProfessionRepository professionRepository;
	@Autowired
	private RequestRepository requestRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public void updateProfession(User user, Set<Profession> professions) {
		Set<Profession> profSet = new HashSet<Profession>();

		for (Profession prof : professions) {
			Profession userProfession = professionRepository.findByName(prof.getName());
			profSet.add(userProfession);
		}

		user.setProfessions(profSet);
		userRepository.save(user);
	}

	@Override
	public void takeRequest(User user, long requestId) {
//		Request request = requestRepository.findById(requestId);
//
//		Set<Request> requests = new HashSet<>();
//		requests = user.getRequestsContractors();
//
//		requests.add(request);
//
//		user.setRequestsContractors(requests);
//		userRepository.save(user);
	}

	@Override
	public void addAdminPermissions(User user) {
		Role adminRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void changeStatus(int id) {
		User tempUser = userRepository.findById(id);

		if(tempUser.getActive() == 0) {
			tempUser.setActive(1);
		}
		else {
			tempUser.setActive(0);
		}
		userRepository.save(tempUser);
	}
}
