package com.freelancerworld.controller;

import com.freelancerworld.model.Profession;
import com.freelancerworld.model.User;
import com.freelancerworld.model.UserProfessionContext;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/findbyid", method = RequestMethod.POST)
    public @ResponseBody User findById(@RequestBody User user) {
        return userService.findUserById(user.getId());
    }

    @RequestMapping(value = "/professionadd", method = RequestMethod.PUT)
    public @ResponseBody User addProfession(@RequestBody UserProfessionContext userProfessionContext) {
        List<Profession> listOfProfessions = userProfessionContext.getProfessions();
        for(Profession prof : listOfProfessions) {
            System.out.println("ELEMENT: "+prof.getName());
        }
        User tempUser = userService.findUserById(userProfessionContext.getUser().getId());

        //System.out.println("PROFESJA: " +userProfessionContext.getProfession().getName());
        //tempUser.setProfessions(user.getProfessions());

        //userService.updateProfession(tempUser);

        return userService.findUserById(userProfessionContext.getUser().getId());
    }
}
