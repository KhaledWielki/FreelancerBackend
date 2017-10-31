package com.freelancerworld.controller;

import com.freelancerworld.model.User;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AdamR on 2017-10-31.
 */

@RestController
@RequestMapping("/user")
public class RESTController {

    @Autowired
    private UserServiceImpl UserService;

    @RequestMapping("/getget")
    public List<User> findAll() {
        return UserService.findAll();
    }


}
