package com.freelancerworld.controller;

import com.freelancerworld.model.*;
import com.freelancerworld.model.contexts.UserAddressRequestProfessionContext;
import com.freelancerworld.service.Implementation.AddressServiceImpl;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Patron on 10.11.2017.
 */
@RestController
@RequestMapping("/request")
public class RESTRequestController {

    private int YES = 1;
    private int NO = 0;

    @Autowired
    private RequestServiceImpl requestService;

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProfessionServiceImpl professionService;

    @RequestMapping("/getall")
    public List<Request> findAll() {
        return requestService.findAllRequests();
    }

    @RequestMapping(value = "/getrequest/{requestId}")
    public Request getSelectedRequest(@PathVariable long requestId) {
            Request tempRequest = requestService.findRequestById(requestId);
            return tempRequest;
    }

    @RequestMapping(value = "/newrequest", method = RequestMethod.POST)
    public @ResponseBody Message addRequest(@RequestBody UserAddressRequestProfessionContext context) {
            addressService.saveAddress(context.getAddress());

            User tempUser = userService.findUserById(context.getUser().getId());
            Profession tempProfession = professionService.findProfessionByName(context.getProfession().getName());

            context.getRequest().setAddress(context.getAddress());
            context.getRequest().setUser(tempUser);
            context.getRequest().setProfession(tempProfession);
            context.getRequest().setActive(YES);
            context.getRequest().setRequestTakerId(0);

            LocalDate todayLocalDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);

            context.getRequest().setCreationDate(sqlDate);

            requestService.saveRequest(context.getRequest());
            return new Message(201, "Success!");
    }


    /**
     *
     * @param requestId
     * @param requestTakerId
     * @return new Message
     *
     */
    @RequestMapping(value = "/addrequesttaker/{requestId}/{requestTakerId}", method = RequestMethod.POST)
    public Message addRequestTakerToRequest(@PathVariable int requestId, @PathVariable int requestTakerId) {
            Request tempRequest = requestService.findRequestById(requestId);

            if(userService.findUserById(requestTakerId) != null) {
                tempRequest.setRequestTakerId(requestTakerId);
                requestService.saveRequest(tempRequest);
                User requestTaker = userService.findUserById(requestTakerId);
                return new Message(201, "Great! You selected " + requestTaker.getName() + " " + requestTaker.getLastName());
            } else {
                return new Message(202, "Sorry, something bad happened");
            }
    }

    @RequestMapping(value = "/showcontractors/{requestId}", method = RequestMethod.POST)
    public Set<User> showContractors(@PathVariable("requestId") int requestId) {
        Request request = requestService.findRequestById(requestId);
        Set<User> contractors = request.getContractors();

        return contractors;
    }
}