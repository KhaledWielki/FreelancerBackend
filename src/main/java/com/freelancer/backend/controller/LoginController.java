package com.freelancer.backend.controller;

import com.freelancer.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.validation.Valid;

/**
 * Created by AdamR on 2017-10-28.
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private ModelAndView modelAndView;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Adres email jest już zajęty");
        }

        if( bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        }
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Rejestracja zakończyła się sukcesem");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        modelAndView.addObject("userName", "Witaj "+ user.getFirstName() +" "+ user.getLastName() +" ("+ user.getEmail() +")");
        modelAndView.addObject("adminMessage", "Nie masz uprawnień aby zobaczyć tę stronę");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}
