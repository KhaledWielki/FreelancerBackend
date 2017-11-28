package com.freelancerworld.controller;

import com.freelancerworld.model.*;
import com.freelancerworld.model.contexts.UserProfessionContext;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        User tempUser = userService.findUserByEmail(user.getEmail());
        boolean doesExist = bCryptPasswordEncoder.matches(user.getPassword(), pass);
        if(doesExist == false || tempUser.getActive() == 0) {
            tempUser = null;
        }
        return tempUser;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Message register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists == null) {
            userService.saveUser(user);
            return new Message(1, "User has been registered");
        } else {
            return new Message (0, "Registration failed - user exists");
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
        LocalDate todayLocalDate = LocalDate.now();

        List<Request> requestsForSpecificUser = new ArrayList<Request>();
        for (Request req : requests) {
            for (Profession prof : professions) {
                if (prof.getName() == req.getProfession().getName() && req.getActive() == 1 && req.getRequestTakerId() != userId) {
                    if(ChronoUnit.DAYS.between(req.getCreationDate().toLocalDate(),todayLocalDate) <= 7) {
                        requestsForSpecificUser.add(req);
                    }
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

    @RequestMapping(value = "/contractoradd/{userId}/{requestId}", method = RequestMethod.POST)
    public Message addContractor(@PathVariable("userId") int userId, @PathVariable("requestId") int requestId) {
        User user = userService.findUserById(userId);
        Request tempRequest = requestService.findRequestById(requestId);

        if(userId == tempRequest.getUser().getId()) {
            return new Message (203, "Cannot accept own request");
        }

        else {
            int allRequests = user.getRequestsContractors().size();
            userService.takeRequest(user, requestId);
            if (user.getRequestsContractors().size() > allRequests) {
                return new Message(200, "You applied to request");
            } else {
                return new Message(201, "FAILURE - request has not been taken");
            }
        }
    }

    @RequestMapping(value = "/showacceptedrequests/{userId}", method = RequestMethod.POST)
    public Set<Request> showAcceptedRequests(@PathVariable("userId") int userId) {
        User user = userService.findUserById(userId);
        Set<Request> acceptedRequests = user.getRequestsContractors();
        return acceptedRequests;
    }

    @RequestMapping(value = "/getportfolio/{requestTakerId}", method = RequestMethod.GET)
    public List<Request> getUserPortfolio(@PathVariable int requestTakerId) {
        int activeNo = 0;
        int maxRequiredCountOfRequests = 3;

        List<Request> portfolioList = requestService.findRequestsByUserTakerIdAndActive(requestTakerId, activeNo);
        if(portfolioList.size() > maxRequiredCountOfRequests) {
            List<Request> listWith3Requests = portfolioList.subList(0, 3);
            return listWith3Requests;
        }
        else {
            return portfolioList;
        }
    }

    @RequestMapping(value = "/editdescription", method = RequestMethod.PATCH)
    public @ResponseBody Message editDescriptionOfUser(@RequestBody User userToEdit) {
        User tempUser= userService.findUserById(userToEdit.getId());
        if(tempUser == null) {
            return new Message(202, "Failure, user doesn't exist");
        }
        else {
            tempUser.setDescription(userToEdit.getDescription());
            userService.saveEditedUser(tempUser);
            return new Message(201, "Success");
        }
    }
}