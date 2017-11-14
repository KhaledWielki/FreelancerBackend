package com.freelancerworld.controller;

import com.freelancerworld.model.*;
import com.freelancerworld.model.contexts.UserProfessionContext;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by AdamR on 2017-10-31.
 */

@RestController
@RequestMapping("/user")
public class RESTUserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RequestServiceImpl requestService;

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
    public @ResponseBody
    Message register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists == null) {
            userService.saveUser(user);
            return new Message(1, "User has been registered");
        } else {
            return new Message (0, "Registration failed");
        }
    }

    @RequestMapping(value = "/findbyid", method = RequestMethod.POST)
    public @ResponseBody User findById(@RequestBody User user) {
        return userService.findUserById(user.getId());
    }

    @RequestMapping(value = "/findrequests/{userId}")
    public List<Request> getRequestsForSpecificUser (@PathVariable("userId") int userId) {
        User user = userService.findUserById(userId);
        Set<Profession> professions = user.getProfessions();

        List<Request> requests = requestService.findAllRequests();

        List<Request> requestsForSpecificUser = new ArrayList<Request>();
        for (Request req : requests) {
            for (Profession prof : professions) {
                if ((prof.getName() == req.getProfession().getName()) && req.getActive() == 1) {
                    requestsForSpecificUser.add(req);
                }
            }
        }
        return requestsForSpecificUser;
    }

    @RequestMapping(value = "/professionadd", method = RequestMethod.PUT)
    public @ResponseBody User addProfession(@RequestBody UserProfessionContext userProfessionContext) {
        Set<Profession> setOfProfessions = userProfessionContext.getProfessions();
        User tempUser = userService.findUserById(userProfessionContext.getUser().getId());

        userService.updateProfession(tempUser, setOfProfessions);

        return userService.findUserById(userProfessionContext.getUser().getId());
    }
}
