package com.freelancerworld.controller.web;

import com.freelancerworld.model.User;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import com.freelancerworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> usersList = userService.findAll();
        model.addAttribute("userslist", usersList);
        return "admin/users";
    }

    @RequestMapping(value = "/admin/users/changestatus/{id}")
    public String changeStatus(@PathVariable int id) {
        userService.changeStatus(id);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {
        User tempUser = userService.findUserById(id);
        model.addAttribute("user", tempUser);
        return "admin/userform";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveEditedUser(User user) {
        if(userService.findUserById(user.getId())!= null) {
            userService.saveEditedUser(user);
        }
        return "redirect:/admin/user/show/" + user.getId();
    }

    @RequestMapping(value = "admin/user/show/{id}")
    public String showUser(@PathVariable int id, Model model) {
        User tempUser = userService.findUserById(id);
        model.addAttribute("user", tempUser);
        return "admin/usershow";
    }
}
