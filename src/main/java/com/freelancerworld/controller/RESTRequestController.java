package com.freelancerworld.controller;

import com.freelancerworld.model.*;
import com.freelancerworld.model.contexts.UserAddressRequestProfessionContext;
import com.freelancerworld.service.Implementation.AddressServiceImpl;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @RequestMapping(value = "/addrequesttaker/{requestId}/{requestTakerId}")
        public Message addRequestTakerToRequest(@PathVariable int requestId, @PathVariable int requestTakerId) {
                Request tempRequest = requestService.findRequestById(requestId);

                if(userService.findUserById(requestTakerId) != null) {
                    tempRequest.setRequestTakerId(requestTakerId);
                    requestService.saveRequest(tempRequest);
                    User requestTaker = userService.findUserById(requestTakerId);
                    return new Message(201, "Great! You selected " + requestTaker.getName() + " " + requestTaker.getLastName());
                }
                else {
                    return new Message(202, "Sorry, something bad happened");
                }
        }
}
