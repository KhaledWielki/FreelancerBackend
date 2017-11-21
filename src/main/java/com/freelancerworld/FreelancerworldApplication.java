package com.freelancerworld;

import com.freelancerworld.model.Address;
import com.freelancerworld.service.Implementation.AddressServiceImpl;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreelancerworldApplication {


	public static void main(String[] args) {
		SpringApplication.run(FreelancerworldApplication.class, args);
	}
}
