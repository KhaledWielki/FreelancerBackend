package com.freelancerworld.controller;

import com.freelancerworld.model.User;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AdamR on 2017-10-31.
 */

@RestController
@RequestMapping("/user")
public class RESTUserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/getget")
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody User signIn(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pass = userService.findUserByEmail(user.getEmail()).getPassword();
        return bCryptPasswordEncoder.matches(user.getPassword(), pass) ? userService.findUserByEmailAndPassword(user.getEmail(), pass) : null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody void register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists == null) {
            userService.saveUser(user);
        }
    }
}
