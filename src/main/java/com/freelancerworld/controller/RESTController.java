package com.freelancerworld.controller;

import com.freelancerworld.model.User;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AdamR on 2017-10-31.
 */

@RestController
@RequestMapping("/user")
public class RESTController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/getget")
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody User signIn(@RequestBody User user) {
        return userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public @ResponseBody User register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists == null) {
            userService.saveUser(user);
        }

        return userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
